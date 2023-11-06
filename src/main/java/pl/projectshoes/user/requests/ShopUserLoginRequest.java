package pl.projectshoes.user.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public record ShopUserLoginRequest (
        @Email(message = "Podano niepoprawny adres e-mail")
        @NotBlank(message = "Nie podałeś adresu e-mail")
        @Size(min = 6,max = 48, message = "Adres e-mail musi składać się z 6-48 znaków")
        String email,
        @NotBlank(message = "Nie podałeś hasła")
        String password) {
}
