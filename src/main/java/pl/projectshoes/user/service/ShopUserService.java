package pl.projectshoes.user.service;

import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.model.ShopUserRole;
import pl.projectshoes.user.requests.ShopUserRegisterRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ShopUserService {
    boolean isShopUserExist(String email);

    Optional<ShopUser> getShopUserByEmail(String email);

    void createShopUser(ShopUserRegisterRequest shopUserRegisterRequest, ShopUserRole defaultRole);

    List<ShopUser> getAllShopUsers();
}
