package pl.projectshoes.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.repository.ShopUserRepository;
import pl.projectshoes.user.requests.ShopUserRegisterRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShopUserService {

    private final ShopUserRepository shopUserRepository;



    @Cacheable(cacheNames = "shopUserByEmail", key = "#email")
    public boolean isShopUserExist(String email){
        return shopUserRepository.existsByEmail(email);
    }

    @Cacheable(cacheNames = "shopUserByEmail", key = "#email")
    public Optional<ShopUser> getShopUserByEmail(String email){
        return shopUserRepository.getShopUserByEmail(email);
    }
    @CachePut(cacheNames = "shopUserByEmail",key = "#shopUserRegisterRequest.email()")
    public void createShopUser(ShopUserRegisterRequest shopUserRegisterRequest){
        shopUserRepository.save(new ShopUser(shopUserRegisterRequest.firstName(),shopUserRegisterRequest.lastName(),shopUserRegisterRequest.email(),shopUserRegisterRequest.password(),shopUserRegisterRequest.phone(),true,true,false, LocalDateTime.now(), Set.of()));
    }

    @Cacheable(cacheNames = "shopUsers")
    public List<ShopUser> getAllUsers(){
        return shopUserRepository.findAll();
    }
}
