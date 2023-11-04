package pl.projectshoes.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectshoes.user.model.ShopUserRole;
import pl.projectshoes.user.repository.UserRoleRepository;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;


    public ShopUserRole getUserRoleByName(String name){
        return userRoleRepository.getUserRoleByName(name);
    }

    boolean isUserRoleExist(String name){
        return userRoleRepository.existsUserRoleByName(name);
    }
}
