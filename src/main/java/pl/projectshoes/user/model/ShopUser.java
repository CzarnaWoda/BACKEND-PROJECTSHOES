package pl.projectshoes.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ShopUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shopUser_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<ShopUserRole> roles = new HashSet<>();



    public ShopUser(String firstName, String lastName, String email, String password, String phone, boolean enabled, boolean isNotLocked, boolean isUsingMfa, LocalDateTime createdAt, Set<ShopUserRole> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.enabled = enabled;
        this.isNotLocked = isNotLocked;
        this.isUsingMfa = isUsingMfa;
        this.createdAt = createdAt;
        this.roles = roles;

        //STANDARD ROLE
    }

    public void addRole(ShopUserRole shopUserRole){
        this.roles.add(shopUserRole);
    }
}
