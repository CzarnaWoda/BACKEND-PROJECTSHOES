package pl.projectshoes.user.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import pl.projectshoes.user.model.ShopUser;

@Component
public class ShopUserDTOMapper {
    public ShopUserDTO fromUser(ShopUser user){
        final ShopUserDTO shopUserDTO = new ShopUserDTO();
        BeanUtils.copyProperties(user, shopUserDTO);
        return shopUserDTO;
    }

}
