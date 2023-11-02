package pl.projectshoes.product.dto;

import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;

import java.awt.*;
import java.util.Date;

public class ProductDTO {
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
