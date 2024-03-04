package pl.projectshoes.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
public class ShopUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String permissions;

    public ShopUserRole(String name, String permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public ShopUserRole() {

    }

}
