package pl.projectshoes.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.projectshoes.security.service.ShopUserDetailsService;


@Component
@RequiredArgsConstructor

public class AccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final ShopUserDetailsService authDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        final String password = userDetails.getPassword();
        final String credentials = (String) authentication.getCredentials();

        if(credentials == null || password == null){
            throw new BadCredentialsException("Can't be null");
        }
        if(!passwordEncoder.matches(credentials,password)){
            throw new BadCredentialsException("Typed password is wrong");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return authDetailsService.loadUserByUsername(username);
    }
}
