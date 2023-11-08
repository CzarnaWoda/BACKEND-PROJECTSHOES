package pl.projectshoes.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectshoes.product.dto.ProductDTO;
import pl.projectshoes.product.dto.ProductDTOMapper;
import pl.projectshoes.product.model.Product;
import pl.projectshoes.product.repository.ProductRepository;
import pl.projectshoes.product.requests.ProductCreateRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceOld {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

//    public boolean isProductExist(long id) {
//        return productRepository.existsById(id);
//    }

    public Optional<Product> getProductById(long id) {
        return productRepository.getProductById(id);
    }

    public Optional<ProductDTO> mapToProductDTO(long id) {
        final Optional<Product> product = getProductById(id);
        return product.map(productDTOMapper::fromProduct);
    }

    public void createProduct(ProductCreateRequest productCreateRequest) {
       //. productRepository.save(new Product(productCreateRequest.category(), productCreateRequest.brand(), productCreateRequest.model(), productCreateRequest.shoeColor(), productCreateRequest.size(), productCreateRequest.price(), productCreateRequest.productCode(), productCreateRequest.quantity(), productCreateRequest.description(), productCreateRequest.image(), productCreateRequest.isAvailable(), false, true, true, productCreateRequest.isOnPromotion(), LocalDateTime.now()));
    }

    public boolean isProductExist(String s) {
        return productRepository.existsByProductCode(s);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
