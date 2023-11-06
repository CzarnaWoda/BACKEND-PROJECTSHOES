package pl.projectshoes.user.service;

import pl.projectshoes.user.model.ShopUserRole;

public interface ShopUserRoleService {

    ShopUserRole getShopUserRoleByName(String name);

    boolean isShopUserRoleExist(String name);

    ShopUserRole createShopUserRole(String name, String permissions);

}
