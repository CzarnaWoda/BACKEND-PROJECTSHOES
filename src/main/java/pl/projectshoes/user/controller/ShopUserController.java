package pl.projectshoes.user.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.projectshoes.security.jwt.JwtAuthenticationFilter;
import pl.projectshoes.security.jwt.TokenService;
import pl.projectshoes.security.provider.AccountAuthenticationProvider;
import pl.projectshoes.user.dto.ShopUserDTO;
import pl.projectshoes.user.dto.ShopUserDTOMapper;
import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.requests.ShopUserChangePasswordRequest;
import pl.projectshoes.user.requests.ShopUserLoginRequest;
import pl.projectshoes.user.requests.ShopUserRegisterRequest;
import pl.projectshoes.user.service.ShopUserRoleService;
import pl.projectshoes.user.service.ShopUserService;
import pl.projectshoes.utils.HttpResponse;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class ShopUserController {

    private final ShopUserService shopUserService;
    private final AccountAuthenticationProvider authenticationProvider;
    private final TokenService tokenService;
    private final ShopUserDTOMapper shopUserDTOMapper;
    private final ShopUserRoleService shopUserRoleService;

    @GetMapping("/{email}")
    public ResponseEntity<HttpResponse> getShopUserByEmail(@PathVariable String email){
        final Optional<ShopUserDTO> shopUserDTO = shopUserService.getShopUserByEmail(email).map(shopUserDTOMapper::fromShopUser);
        if(shopUserDTO.isPresent()){
            return ResponseEntity.status(OK).body(HttpResponse.builder()
                    .status(OK)
                    .statusCode(OK.value())
                    .data(Map.of("user",shopUserDTO))
                    .timeStamp(now().toString())
                    .build());
        }else{
            return ResponseEntity.status(NOT_FOUND).body(HttpResponse.builder()
                    .timeStamp(now().toString())
                    .status(NOT_FOUND)
                    .statusCode(NOT_FOUND.value())
                    .developerMessage("User was not found in repository !")
                    .data(Map.of("email",email))
                    .build());
        }
    }

    @GetMapping("/userDetails")
    public ResponseEntity<HttpResponse> getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() && authentication instanceof JwtAuthenticationToken) {
            final String email = authentication.getName();

            final Optional<ShopUser> user  = shopUserService.getShopUserByEmail(email);

            HttpResponse.HttpResponseBuilder<?, ?> httpResponse = HttpResponse.builder();
            user.ifPresentOrElse(shopUser -> {
                httpResponse.data(Map.of("user",shopUserDTOMapper.fromShopUser(shopUser)));
                httpResponse.status(OK);
                httpResponse.statusCode(OK.value());
            },() -> {
                httpResponse.status(BAD_GATEWAY);
                httpResponse.statusCode(BAD_GATEWAY.value());
            });

            return ResponseEntity.status(ACCEPTED).body(httpResponse.build());


        }
        return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                .timeStamp(now().toString())
                .status(BAD_REQUEST)
                .statusCode(BAD_REQUEST.value())
                .build());
    }
    @PostMapping("/register")
    public ResponseEntity<HttpResponse> createShopUser(@RequestBody @Valid ShopUserRegisterRequest shopUserRegisterRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                    .timeStamp(now().toString())
                    .status(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .message(bindingResult.getAllErrors().get(0).getDefaultMessage())
                    .reason(bindingResult.getAllErrors().get(0).getDefaultMessage())
                    .build());
        }
        if(shopUserService.isShopUserExist(shopUserRegisterRequest.email())){
            return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                    .timeStamp(now().toString())
                    .status(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .message("User with that email already exist !")
                    .developerMessage("User with that email already exist !")
                    .data(Map.of("Request",shopUserRegisterRequest))
                    .build());
        }else{
            shopUserService.createShopUser(shopUserRegisterRequest, shopUserRoleService.getShopUserRoleByName("ROLE_USER"));
            return ResponseEntity.status(CREATED).body(HttpResponse.builder()
                    .timeStamp(now().toString())
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .developerMessage("User created!")
                    .build());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody ShopUserLoginRequest loginRequest) {
        try{
            final Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password())
            );

            final String token = tokenService.generateToken(authentication, loginRequest.remember());


            return ResponseEntity.status(OK).body(HttpResponse.builder()
                            .timeStamp(now().toString())
                            .status(OK)
                            .statusCode(OK.value())
                            .data(Map.of("user",shopUserDTOMapper.fromShopUser(shopUserService.getShopUserByEmail(loginRequest.email()).get())))
                            .message(token)
                    .build());
        }catch (AuthenticationException e){
            return ResponseEntity.status(UNAUTHORIZED).body(HttpResponse
                    .builder()
                    .message("Wrong login or password")
                    .timeStamp(now().toString())
                    .statusCode(UNAUTHORIZED.value())
                    .status(UNAUTHORIZED)
                    .data(Map.of("AuthenticationException",e.getMessage()))
                    .developerMessage("TEST 1").build());
        }
    }
    @GetMapping("/validate")
    public ResponseEntity<HttpResponse> validate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() && authentication instanceof JwtAuthenticationToken) {
            return ResponseEntity.status(OK).body(HttpResponse.builder()
                    .message("Token is valid")
                    .timeStamp(now().toString())
                    .statusCode(OK.value())
                    .data(Map.of("user",shopUserDTOMapper.fromShopUser(shopUserService.getShopUserByEmail(authentication.getName()).get())))
                    .status(OK).build());
        }else{
            return ResponseEntity.status(UNAUTHORIZED).body(HttpResponse.builder()
                    .message("Token is not valid")
                    .timeStamp(now().toString())
                    .statusCode(UNAUTHORIZED.value())
                    .status(UNAUTHORIZED).build());
        }
    }
    @GetMapping("admin")
    public ResponseEntity<HttpResponse> admin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() && authentication instanceof JwtAuthenticationToken) {
            System.out.println(authentication.getAuthorities());
            final Optional<ShopUser> user = shopUserService.getShopUserByEmail(authentication.getName());
            if(user.isPresent()){
                if(user.get().getRoles().contains(shopUserRoleService.getShopUserRoleByName("ROLE_ADMIN"))) {
                    return ResponseEntity.status(OK).body(HttpResponse.builder()
                            .message("Admin access")
                            .timeStamp(now().toString())
                            .statusCode(OK.value())
                            .status(OK).build());
                }
            }
            return ResponseEntity.status(OK).body(HttpResponse.builder()
                    .message("You don't have admin permissions")
                    .timeStamp(now().toString())
                    .statusCode(OK.value())
                    .status(OK).build());
        }else{
            return ResponseEntity.status(UNAUTHORIZED).body(HttpResponse.builder()
                    .message("You don't have admin permissions")
                    .timeStamp(now().toString())
                    .statusCode(UNAUTHORIZED.value())
                    .status(UNAUTHORIZED).build());
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<HttpResponse> changePassword(@RequestBody @Valid ShopUserChangePasswordRequest request, BindingResult bindingResult){
        try{
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(),request.password())
            );
            if(bindingResult.hasErrors()){
                return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                        .timeStamp(now().toString())
                        .status(BAD_REQUEST)
                        .statusCode(BAD_REQUEST.value())
                        .message(bindingResult.getAllErrors().get(0).getDefaultMessage())
                        .reason(bindingResult.getAllErrors().get(0).getDefaultMessage())
                        .build());
            }
            System.out.println(request.toString());
            shopUserService.updatePasswordByEmail(request.email(),request.newPassword());
            return ResponseEntity.status(OK).body(HttpResponse.builder()
                    .timeStamp(now().toString())
                    .status(OK)
                    .statusCode(OK.value())
                    .data(Map.of("user",shopUserDTOMapper.fromShopUser(shopUserService.getShopUserByEmail(request.email()).get())))
                    .message("Password updated")
                    .build());
        }catch (AuthenticationException e){
            return ResponseEntity.status(UNAUTHORIZED).body(HttpResponse
                    .builder()
                    .message("Wrong login or password")
                    .timeStamp(now().toString())
                    .statusCode(UNAUTHORIZED.value())
                    .status(UNAUTHORIZED)
                    .data(Map.of("AuthenticationException",e.getMessage()))
                    .developerMessage("TEST 1").build());
        }
    }
}
