package pl.projectshoes.user.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ShopUserRegisterRequest(
        @NotBlank(message = "Nie podano imienia")
        @Size(min = 4, max = 32, message = "Imie musi skladac sie od 4 do 32 znaków")
        String firstName,
        @NotBlank(message = "Nie podano nazwiska")
        @Size(min = 4, max = 32, message = "Nazwisko musi skladac sie od 4 do 32 znaków")
        String lastName,
        @NotBlank(message = "Nie podano emaila")
        @Email(message = "Nie podano poprawnego email'a")
        String email,
        @NotBlank(message = "Nie podano poprawnego hasła")
        @Size(min=10,max=128)
        String password,
        String phone
) {
}
