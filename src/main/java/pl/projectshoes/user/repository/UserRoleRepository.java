package pl.projectshoes.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projectshoes.user.model.ShopUserRole;


@Repository
public interface UserRoleRepository extends JpaRepository<ShopUserRole,Long> {


    ShopUserRole getUserRoleByName(String userRoleName);

    boolean existsUserRoleByName(String userRoleName);
    //ADMIN USER

    //TODO change methods name
}
