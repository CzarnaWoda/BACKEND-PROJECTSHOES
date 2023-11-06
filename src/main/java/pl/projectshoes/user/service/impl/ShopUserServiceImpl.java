package pl.projectshoes.user.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.model.ShopUserRole;
import pl.projectshoes.user.repository.ShopUserRepository;
import pl.projectshoes.user.requests.ShopUserRegisterRequest;
import pl.projectshoes.user.service.ShopUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
class ShopUserServiceImpl implements ShopUserService {

    private final ShopUserRepository shopUserRepository;


    @Override
    @Cacheable(cacheNames = "shopUserByEmail", key = "#email")
    public Optional<ShopUser> getShopUserByEmail(String email){
        return shopUserRepository.getByEmail(email);
    }
    @Override
    @CachePut(cacheNames = "shopUserByEmail",key = "#result.email")
    public ShopUser createShopUser(ShopUserRegisterRequest shopUserRegisterRequest, ShopUserRole defaultRole){
        return shopUserRepository.save(new ShopUser(shopUserRegisterRequest.firstName(),shopUserRegisterRequest.lastName(),shopUserRegisterRequest.email(),shopUserRegisterRequest.password(),shopUserRegisterRequest.phone(),true,true,false, LocalDateTime.now(), Set.of(defaultRole)));
    }
    @Override
    @Cacheable(cacheNames = "shopUsers")
    public List<ShopUser> getAllShopUsers(){
        return shopUserRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "shopUserByEmail", key = "#email")
    public boolean isShopUserExist(String email) {
        return shopUserRepository.existsByEmail(email);
    }
}
