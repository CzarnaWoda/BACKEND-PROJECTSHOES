package pl.projectshoes.user.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
        @NotBlank(message = "Nie podano poprawnego numeru telefonu")
        @Size(min = 9, max = 9, message = "Numer telefonu składa sie z 9 cyfr")
        @Pattern(regexp = "^[1-9][0-9]{8}$", message = "Wprowadzono niepoprawny numer telefonu")
        String phone
) {
}
