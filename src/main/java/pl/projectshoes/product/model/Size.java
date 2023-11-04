package pl.projectshoes.product.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.projectshoes.product.enums.Standard;

import java.io.Serializable;


@Embeddable
@NoArgsConstructor
@Data
public class Size {
    private double size;
    private Standard standard;

    public Size(double size, Standard standard) {
        this.size = size;
        this.standard = standard;
    }
}
