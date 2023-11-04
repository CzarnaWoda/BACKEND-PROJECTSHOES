package pl.projectshoes.user.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.projectshoes.user.dto.ShopUserDTO;
import pl.projectshoes.user.requests.ShopUserRegisterRequest;
import pl.projectshoes.user.service.ShopUserService;
import pl.projectshoes.utils.HttpResponse;

import java.util.Map;
import java.util.Optional;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class ShopUserController {

    private final ShopUserService shopUserService;


    @GetMapping("/{email}")
    public ResponseEntity<HttpResponse> getShopUserByEmail(@PathVariable String email){
        final Optional<ShopUserDTO> shopUserDTO = shopUserService.mapToShopUserDTO(email);
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
    @PostMapping("/register")
    public ResponseEntity<HttpResponse> createShopUser(@RequestBody @Valid ShopUserRegisterRequest shopUserRegisterRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                    .timeStamp(now().toString())
                    .status(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .reason(bindingResult.getAllErrors().get(0).getDefaultMessage())
                    .build());
        }
        if(shopUserService.isShopUserExist(shopUserRegisterRequest.email())){
            return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                    .timeStamp(now().toString())
                    .status(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .developerMessage("User with that email already exist !")
                    .data(Map.of("Request",shopUserRegisterRequest))
                    .build());
        }else{
            shopUserService.createShopUser(shopUserRegisterRequest);
            return ResponseEntity.status(CREATED).body(HttpResponse.builder()
                    .timeStamp(now().toString())
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .developerMessage("User created!")
                    .build());
        }
    }
}
