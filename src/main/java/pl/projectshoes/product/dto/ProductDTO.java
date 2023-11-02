package pl.projectshoes.product.dto;

import lombok.Data;
import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class ProductDTO implements Serializable {
    private Category category;
    private Brand brand;
    private String model;
    private Color color;
    private double size;
    private double price;
    private String productCode;
    private String description;
    private String image;
}
