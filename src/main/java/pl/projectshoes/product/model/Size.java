package pl.projectshoes.product.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.projectshoes.product.enums.Standard;


@Embeddable
@NoArgsConstructor
@Data
public class Size {

    private double size;
    private Standard standard;
    private int quantity;

    public Size(double size, Standard standard, int quantity) {
        this.size = size;
        this.standard = standard;
        this.quantity = quantity;
    }
}
