package pl.projectshoes.product;

import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;

import java.awt.*;
import java.util.Date;

public record Product(
        Integer productId,
        Category category,
        Brand brand,
        String model,
        Color color,
        Double size,
        Double price,
        String productCode,
        Integer quantity,
        String description,
        String image,
        Boolean isAvailable,
        Boolean isBestseller,
        Boolean isNew,
        Boolean isRecommended,
        Boolean isOnPromotion,
        Date createdAt
) {
}
