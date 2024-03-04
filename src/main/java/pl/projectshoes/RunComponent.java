package pl.projectshoes;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;
import pl.projectshoes.product.enums.ShoeColor;
import pl.projectshoes.product.enums.Standard;
import pl.projectshoes.product.model.Size;
import pl.projectshoes.product.requests.ProductCreateRequest;
import pl.projectshoes.product.service.ProductService;
import pl.projectshoes.user.requests.ShopUserRegisterRequest;
import pl.projectshoes.user.service.ShopUserRoleService;
import pl.projectshoes.user.service.ShopUserService;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
class RunComponent implements CommandLineRunner {

    private final ShopUserRoleService shopUserRoleService;
    private final ProductService productService;
    private final ShopUserService shopUserService;
    @Override
    public void run(String... args) {

        if(!shopUserRoleService.isShopUserRoleExist("ROLE_USER")){
            shopUserRoleService.createShopUserRole("ROLE_USER","READ:USER,WRITE:USER");
        }
        if(!shopUserRoleService.isShopUserRoleExist("ROLE_ADMIN")){
            shopUserRoleService.createShopUserRole("ROLE_ADMIN","READ:ADMIN,WRITE:ADMIN");
        }
        if(productService.getAllProducts().isEmpty()){
            productService.createProduct(new ProductCreateRequest(Category.MAN, Brand.ADIDAS,"XD", ShoeColor.AQUA, Arrays.asList(new Size(2.2,Standard.EUR,1)),22,"CODE-TEST","DESCRIPTION","https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/edd9ed4ed4a0438bae1cadb901365eee_9366/RUNMAGICA_SHOES_Black_EY2975_01_standard.jpg",true,true));

            productService.createProduct(new ProductCreateRequest(Category.WOMAN, Brand.NEW_BALANCE,"XD1", ShoeColor.BLACK,Arrays.asList(new Size(61.2,Standard.EUR,3)),222,"CODE-TEST1","DESCRIPTIOsN","https://t3.ftcdn.net/jpg/02/77/57/08/360_F_277570841_LunmcG0HGCxeRRKb656wgHmQQEP6GX3S.jpg",true,true));

        }
        if(!shopUserService.isShopUserExist("test@test.com")){
            shopUserService.createShopUser(new ShopUserRegisterRequest("Mateusz", "Black" , "test@test.com","chuj789456123","517789666"),shopUserRoleService.getShopUserRoleByName("ROLE_ADMIN"));
        }

    }
}
