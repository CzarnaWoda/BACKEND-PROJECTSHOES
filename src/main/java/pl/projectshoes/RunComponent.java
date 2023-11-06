package pl.projectshoes;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.projectshoes.user.service.ShopUserRoleService;

@Component
@RequiredArgsConstructor
class RunComponent implements CommandLineRunner {

    private final ShopUserRoleService shopUserRoleService;
    @Override
    public void run(String... args) {

        if(!shopUserRoleService.isUserRoleExist("ROLE_USER")){
            shopUserRoleService.createShopUserRole("ROLE_USER","READ:USER,WRITE:USER");
        }
        if(!shopUserRoleService.isUserRoleExist("ROLE_ADMIN")){
            shopUserRoleService.createShopUserRole("ROLE_ADMIN","READ:ADMIN,WRITE:ADMIN");
        }

    }
}
