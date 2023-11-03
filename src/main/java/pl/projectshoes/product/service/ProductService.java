package pl.projectshoes.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectshoes.product.dto.ProductDTO;
import pl.projectshoes.product.dto.ProductDTOMapper;
import pl.projectshoes.product.model.Product;
import pl.projectshoes.product.repository.ProductRepository;
import pl.projectshoes.product.requests.ProductAddRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {
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

    public void addProduct(ProductAddRequest productAddRequest) {
        productRepository.save(new Product(productAddRequest.category(),productAddRequest.brand(),productAddRequest.model(),productAddRequest.shoeColor(),productAddRequest.size(),productAddRequest.price(),productAddRequest.productCode(),productAddRequest.quantity(),productAddRequest.description(),productAddRequest.image(),productAddRequest.isAvailable(), false, true, true, productAddRequest.isOnPromotion(), LocalDateTime.now()));
    }

    public boolean isProductExist(String s) {
        return productRepository.existsByProductCode(s);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
