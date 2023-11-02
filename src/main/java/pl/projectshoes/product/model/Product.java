package pl.projectshoes.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;

import java.awt.*;
import java.util.Date;

public record Product(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        long productId,
        Category category,
        Brand brand,
        String model,
        Color color,
        double size,
        double price,
        String productCode,
        long quantity,
        String description,
        String image,
        boolean isAvailable,
        boolean isBestseller,
        boolean isNew,
        boolean isRecommended,
        boolean isOnPromotion,
        Date createdAt
) {
}
