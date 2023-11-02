package pl.projectshoes.product.repository;

import org.springframework.stereotype.Repository;
import pl.projectshoes.product.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAll();
}
