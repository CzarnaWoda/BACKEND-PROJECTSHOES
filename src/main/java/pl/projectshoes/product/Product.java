package pl.projectshoes.product;

import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;

import java.awt.*;
import java.util.Date;

public record Product(
        int productId,
        Category category,
        Brand brand,
        String model,
        Color color,
        double size,
        double price,
        String productCode,
        int quantity,
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
