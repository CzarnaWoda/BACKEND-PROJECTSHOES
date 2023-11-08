package pl.projectshoes.product.service;

import pl.projectshoes.product.dto.ProductDTO;
import pl.projectshoes.product.model.Product;
import pl.projectshoes.product.requests.ProductCreateRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    boolean isProductExist(String s);

    Product createProduct(ProductCreateRequest productCreateRequest);

//    Optional<Object> getProductById(long id);

    void deleteProduct(String s);

    Optional<Product> getProductByProductCode(String productCode);
}
