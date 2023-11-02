package pl.projectshoes.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectshoes.product.dto.ProductDTO;
import pl.projectshoes.product.dto.ProductDTOMapper;
import pl.projectshoes.product.model.Product;
import pl.projectshoes.product.repository.ProductRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public boolean isProductExist(long id) {
        return productRepository.existsById(id);
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.getProductById(id);
    }

    public Optional<ProductDTO> mapToProductDTO(long id) {
        final Optional<Product> product = getProductById(id);
        return product.map(productDTOMapper::fromProduct);
    }

}
