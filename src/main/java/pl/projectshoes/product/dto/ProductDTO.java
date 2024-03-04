package pl.projectshoes.product.dto;

import lombok.Data;
import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;
import pl.projectshoes.product.enums.ShoeColor;
import pl.projectshoes.product.model.Size;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDTO implements Serializable {
    private Category category;
    private Brand brand;
    private String model;
    private ShoeColor shoeColor;
    private List<Size> size;
    private double price;
    private String productCode;
    private String description;
    private String image;
}
