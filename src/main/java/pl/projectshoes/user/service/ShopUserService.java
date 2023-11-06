package pl.projectshoes.user.service;

import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.model.ShopUserRole;
import pl.projectshoes.user.requests.ShopUserRegisterRequest;

import java.util.List;
import java.util.Optional;

public interface ShopUserService {
    Optional<ShopUser> getShopUserByEmail(String email);

    ShopUser createShopUser(ShopUserRegisterRequest shopUserRegisterRequest, ShopUserRole defaultRole);

    List<ShopUser> getAllShopUsers();
}
