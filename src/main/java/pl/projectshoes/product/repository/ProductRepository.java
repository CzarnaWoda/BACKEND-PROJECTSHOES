package pl.projectshoes.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projectshoes.product.model.Product;

import java.util.List;

@Repository
public interface ProductRepository {

    List<Product> findAll();
}
