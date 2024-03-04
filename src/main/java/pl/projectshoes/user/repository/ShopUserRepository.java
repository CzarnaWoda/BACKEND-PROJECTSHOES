package pl.projectshoes.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projectshoes.user.model.ShopUser;

import java.util.Optional;

@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser,Long> {

    boolean existsByEmail(String email);

    Optional<ShopUser> findByEmail(String email);

}
