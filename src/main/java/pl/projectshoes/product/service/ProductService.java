package pl.projectshoes.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectshoes.product.model.Product;
import pl.projectshoes.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

}
