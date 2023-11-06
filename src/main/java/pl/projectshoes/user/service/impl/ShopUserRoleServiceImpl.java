package pl.projectshoes.user.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.projectshoes.user.model.ShopUserRole;
import pl.projectshoes.user.repository.ShopUserRoleRepository;
import pl.projectshoes.user.service.ShopUserRoleService;

@Service
@RequiredArgsConstructor
class ShopUserRoleServiceImpl implements ShopUserRoleService {

    private final ShopUserRoleRepository shopUserRoleRepository;

    @Override
    @Cacheable(cacheNames = "shopUserRoleByName", key = "#name")
    public ShopUserRole getShopUserRoleByName(String name){
        return shopUserRoleRepository.getByName(name);
    }
    @Cacheable(cacheNames = "shopUserRoleByName", key = "#name")
    @Override
    public boolean isShopUserRoleExist(String name){
        return shopUserRoleRepository.existsByName(name);
    }
    @Override
    @CachePut(cacheNames = "shopUserRoleByName", key = "#result.name")
    public ShopUserRole createShopUserRole(String name, String permissions) {
        return shopUserRoleRepository.save(new ShopUserRole(name,permissions));
    }
}
