package pl.projectshoes.product.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import pl.projectshoes.product.model.Product;

@Component
public class ProductDTOMapper {
    public ProductDTO fromProduct(Product product){
        final ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

}
