package pl.projectshoes.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.projectshoes.security.service.ShopUserDetailsService;

@Component
@RequiredArgsConstructor
class JwtAuthenticationProvider implements AuthenticationProvider {

    private final ShopUserDetailsService shopUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String email = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final UserDetails userDetails = shopUserDetailsService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(email,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
