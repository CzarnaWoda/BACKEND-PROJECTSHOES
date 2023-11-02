package pl.projectshoes.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectshoes.product.dto.ProductDTO;
import pl.projectshoes.product.dto.ProductDTOMapper;
import pl.projectshoes.product.model.Product;
import pl.projectshoes.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDTOMapper productDTOMapper;
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public boolean isProductExist(long id) {
        return productRepository.existsById(id);
    }

    public Product getProductById(long id) {
        return productRepository.getProductById(id);
    }

    public ProductDTO mapToProductDTO(long id) {
        return productDTOMapper.fromProduct(getProductById(id));
    }
}
