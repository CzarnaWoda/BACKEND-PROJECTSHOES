package pl.projectshoes.product.requests;

import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;
import pl.projectshoes.product.enums.ShoeColor;
import pl.projectshoes.product.enums.Standard;
import pl.projectshoes.product.model.Size;

public record ProductCreateRequest(
        Category category,
        Brand brand,
        String model,
        ShoeColor shoeColor,
        int size,
        Standard standard,
        double price,
        String productCode,
        int quantity,
        String description,
        String image,
        boolean isAvailable,
        boolean isOnPromotion
) {
}
