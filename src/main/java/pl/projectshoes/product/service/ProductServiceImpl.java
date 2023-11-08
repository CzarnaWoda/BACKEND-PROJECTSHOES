package pl.projectshoes.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.projectshoes.product.dto.ProductDTO;
import pl.projectshoes.product.enums.Standard;
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
    public Optional<ProductDTO> mapToProductDTO(long id) {
        return Optional.empty();
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
    public Optional<Object> getProductById(long id) {
        return Optional.empty();
    }

    @Override
    public void deleteProduct(long id) {

    }

}
