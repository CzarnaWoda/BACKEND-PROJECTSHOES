package pl.projectshoes.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projectshoes.user.model.ShopUserRole;


@Repository
public interface ShopUserRoleRepository extends JpaRepository<ShopUserRole,Long> {


    ShopUserRole findByName(String name);

    boolean existsByName(String userRoleName);
}
