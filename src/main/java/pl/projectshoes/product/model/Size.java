package pl.projectshoes.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.projectshoes.product.enums.Standard;



@Embeddable
@NoArgsConstructor
public class Size {
    private double size;
    private Standard standard;

    public Size(double size, Standard standard) {
        this.size = size;
        this.standard = standard;
    }
}
