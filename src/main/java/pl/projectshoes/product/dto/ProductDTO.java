package pl.projectshoes.product.dto;

import jakarta.persistence.Embedded;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;
import pl.projectshoes.product.enums.ShoeColor;
import pl.projectshoes.product.enums.Standard;
import pl.projectshoes.product.model.Size;

import java.io.Serializable;

@Data
public class ProductDTO implements Serializable {
    private Category category;
    private Brand brand;
    private String model;
    private ShoeColor shoeColor;
    @Embedded
    private Size size;
    private double price;
    private String productCode;
    private String description;
    private String image;
}
