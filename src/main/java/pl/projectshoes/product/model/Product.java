package pl.projectshoes.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;
import pl.projectshoes.product.enums.ShoeColor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Category category;
    private Brand brand;
    private String model;
    private ShoeColor shoeColor;
    @ElementCollection
    private List<Size> size;
    private double price;
    private String productCode;
    private String description;
    private String image;
    private boolean isAvailable;
    private boolean isBestseller;
    private boolean isNew;
    private boolean isRecommended;
    private boolean isOnPromotion;
    private LocalDateTime createdAt;

    public Product(Category category, Brand brand, String model, ShoeColor shoeColor, List<Size> size, double price, String productCode, String description, String image, boolean isAvailable, boolean isBestseller, boolean isNew, boolean isRecommended, boolean isOnPromotion, LocalDateTime createdAt) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.shoeColor = shoeColor;
        this.size = size;
        this.price = price;
        this.productCode = productCode;
        this.description = description;
        this.image = image;
        this.isAvailable = isAvailable;
        this.isBestseller = isBestseller;
        this.isNew = isNew;
        this.isRecommended = isRecommended;
        this.isOnPromotion = isOnPromotion;
        this.createdAt = createdAt;
    }
}
