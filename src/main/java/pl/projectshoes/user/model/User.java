package pl.projectshoes.user.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private String phone;
    private boolean enabled;
    private boolean isNotLocked;
    private boolean isUsingMfa;
    private LocalDateTime createdAt;

    public User(int id, String firstName, String lastName, String email, String password, String phone, boolean enabled, boolean isNotLocked, boolean isUsingMfa, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.enabled = enabled;
        this.isNotLocked = isNotLocked;
        this.isUsingMfa = isUsingMfa;
        this.createdAt = createdAt;
    }
}
