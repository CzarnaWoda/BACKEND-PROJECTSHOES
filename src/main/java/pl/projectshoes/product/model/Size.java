package pl.projectshoes.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.projectshoes.product.enums.Standard;



public class Size {
    @Column(name = "size_column")
    private double size;
    private Standard standard;
    public Size(double size, Standard standard) {
        this.size = size;
        this.standard = standard;
    }
}
