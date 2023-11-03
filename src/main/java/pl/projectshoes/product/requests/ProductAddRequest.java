package pl.projectshoes.product.requests;

import jakarta.persistence.Embedded;
import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;
import pl.projectshoes.product.enums.ShoeColor;
import pl.projectshoes.product.model.Size;

import java.time.LocalDateTime;

public record ProductAddRequest(
        Category category,
        Brand brand,
        String model,
        ShoeColor shoeColor,
        Size size,
        double price,
        String productCode,
        int quantity,
        String description,
        String image,
        boolean isAvailable,
        boolean isOnPromotion
) {
}
