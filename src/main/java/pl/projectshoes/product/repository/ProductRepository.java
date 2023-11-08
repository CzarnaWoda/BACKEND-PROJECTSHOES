package pl.projectshoes.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projectshoes.product.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAll();

    Optional<Product> getProductByProductCode(String s);

    boolean existsByProductCode(String s);

    void deleteByProductCode(String s);
}
