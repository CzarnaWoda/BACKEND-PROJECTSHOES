package pl.projectshoes.product.requests;

import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;
import pl.projectshoes.product.enums.ShoeColor;
import pl.projectshoes.product.enums.Standard;
import pl.projectshoes.product.model.Size;

import java.util.List;

public record ProductCreateRequest(
        Category category,
        Brand brand,
        String model,
        ShoeColor shoeColor,
        List<Size> size,
        double price,
        String productCode,
        String description,
        String image,
        boolean isAvailable,
        boolean isOnPromotion
) {
}
