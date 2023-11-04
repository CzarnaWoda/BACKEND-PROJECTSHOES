package pl.projectshoes.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectshoes.user.dto.ShopUserDTO;
import pl.projectshoes.user.dto.ShopUserDTOMapper;
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
    private final ShopUserDTOMapper shopUserDTOMapper;



    public boolean isShopUserExist(String email){
        return shopUserRepository.existsByEmail(email);
    }

    public Optional<ShopUser> getShopUserByEmail(String email){
        return shopUserRepository.getShopUserByEmail(email);
    }

    public Optional<ShopUserDTO> mapToShopUserDTO(String email) {
        final Optional<ShopUser> shopUser = getShopUserByEmail(email);
        return shopUser.map(shopUserDTOMapper::fromShopUser);
    }
    public void createShopUser(ShopUserRegisterRequest shopUserRegisterRequest){
        shopUserRepository.save(new ShopUser(shopUserRegisterRequest.firstName(),shopUserRegisterRequest.lastName(),shopUserRegisterRequest.email(),shopUserRegisterRequest.password(),shopUserRegisterRequest.phone(),true,true,false, LocalDateTime.now(), Set.of()));
    }

    public List<ShopUser> getAllUsers(){
        return shopUserRepository.findAll();
    }
}
