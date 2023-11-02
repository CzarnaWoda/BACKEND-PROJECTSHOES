package pl.projectshoes.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ShopUserDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}
