package pl.projectshoes.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.model.ShopUserRole;
import pl.projectshoes.user.service.ShopUserService;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShopUserDetailsService implements UserDetailsService {

    private final ShopUserService shopUserService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<ShopUser> shopUser = shopUserService.getShopUserByEmail(email);

        if(shopUser.isPresent()){
            final ShopUser user = shopUser.get();

            return new User(user.getEmail(),
                    user.getPassword(),
                    user.isEnabled(),
                    true,
                    true,
                    user.isNotLocked(),
                    getAuthorities(user.getRoles())
            );
        }
        throw new UsernameNotFoundException("Email is not correct!");
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<ShopUserRole> roles){
        return roles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getName())).collect(Collectors.toList());
    }
}
