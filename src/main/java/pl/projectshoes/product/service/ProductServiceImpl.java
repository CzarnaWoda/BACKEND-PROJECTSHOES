package pl.projectshoes.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.projectshoes.product.model.Product;
import pl.projectshoes.product.model.Size;
import pl.projectshoes.product.repository.ProductRepository;
import pl.projectshoes.product.requests.ProductCreateRequest;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Object getAllProducts() {
        return null;
    }

    @Override
    @CachePut(cacheNames = "productByProductCode", key = "#productCode")
    public boolean isProductExist(String productCode) {
        return productRepository.existsByProductCode(productCode);
    }

    @Override
    @CachePut(cacheNames = "productByProductCode", key = "#result.productCode")
    public Product createProduct(ProductCreateRequest productCreateRequest) {
        return productRepository.save(new Product(productCreateRequest.category(), productCreateRequest.brand(), productCreateRequest.model(), productCreateRequest.shoeColor(), new Size(productCreateRequest.size(), productCreateRequest.standard()), productCreateRequest.price(), productCreateRequest.productCode(), productCreateRequest.quantity(), productCreateRequest.description(), productCreateRequest.image(), productCreateRequest.isAvailable(), false, true, true, productCreateRequest.isOnPromotion(), LocalDateTime.now()));
    }

    @Override
    @Cacheable(cacheNames = "productByProductCode", key = "#productCode")
    public Optional<Product> getProductByProductCode(String productCode) {
        return productRepository.getProductByProductCode(productCode);
    }

    @Override
    public void deleteProduct(String s) {

    }

}
