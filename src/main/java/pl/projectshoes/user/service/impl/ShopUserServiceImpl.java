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

    //Only for test {Jeżeli ktoś doda użytkownika/ zarejestruje sie to powinno dzięki result.size pobrać na nowo wszystkich użytkowników tylko pytanie czy jak bedzie np 10k użytkowników to czy jak bedzie 10k i 1 to nie bedzie za dużo pamięci brało dla cache więc możliwe że trzeba to zrobić coś w stylu kasowania co 2-3 minuty żeby to faktycznie miało sens optymalizacyjny}
    @Override
    @Cacheable(cacheNames = "shopUsers", key = "#result.size()")
    public List<ShopUser> getAllShopUsers(){
        return shopUserRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "shopUserByEmail", key = "#email")
    public boolean isShopUserExist(String email) {
        return shopUserRepository.existsByEmail(email);
    }
}
