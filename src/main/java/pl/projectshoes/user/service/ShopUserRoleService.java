package pl.projectshoes.user.service;

import pl.projectshoes.user.model.ShopUserRole;

public interface ShopUserRoleService {

    ShopUserRole getUserRoleByName(String name);

    boolean isUserRoleExist(String name);

    ShopUserRole createShopUserRole(String name, String permissions);

}
