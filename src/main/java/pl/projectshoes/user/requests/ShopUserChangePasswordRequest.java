package pl.projectshoes.user.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ShopUserChangePasswordRequest (

    @Email(message = "Podano niepoprawny adres e-mail")
    @NotBlank(message = "Nie podałeś adresu e-mail")
    @NotNull(message = "Nie podano e-mail")
    @Size(min = 6,max = 48, message = "Adres e-mail musi składać się z 6-48 znaków")
    String email,

    @NotBlank(message = "Nie podano poprawnego hasła")
    @NotNull(message = "Nie podano hasła")
    String password,

    @NotBlank(message = "Nie podano poprawnego hasła")
    @NotNull(message = "Nie podano hasła")
    @Size(min=10,max=128,message = "Haslo sklada musi skladac sie z minimum 10 znaków, maksimum 128 znaków")
    String newPassword
){

}
