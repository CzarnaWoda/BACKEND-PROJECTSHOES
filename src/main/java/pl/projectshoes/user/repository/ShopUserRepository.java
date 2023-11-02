package pl.projectshoes.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projectshoes.user.model.ShopUser;

@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser,Long> {

    boolean existsByEmail(String email);

    ShopUser getShopUserByEmail(String email);

}
