package pl.projectshoes.user.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.projectshoes.user.model.ShopUserRole;
import pl.projectshoes.user.repository.UserRoleRepository;
import pl.projectshoes.user.service.ShopUserRoleService;

@Service
@RequiredArgsConstructor
class ShopUserRoleServiceImpl implements ShopUserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Override
    @Cacheable(cacheNames = "shopUserRoleByName", key = "#name")
    public ShopUserRole getUserRoleByName(String name){
        return userRoleRepository.getUserRoleByName(name);
    }
    @Cacheable(cacheNames = "shopUserRoleByName", key = "#name")
    @Override
    public boolean isUserRoleExist(String name){
        return userRoleRepository.existsUserRoleByName(name);
    }
    @Override
    @CachePut(cacheNames = "shopUserRoleByName", key = "#result.name")
    public ShopUserRole createShopUserRole(String name, String permissions) {
        return userRoleRepository.save(new ShopUserRole(name,permissions));
    }
}
