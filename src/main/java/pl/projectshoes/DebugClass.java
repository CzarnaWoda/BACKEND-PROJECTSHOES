package pl.projectshoes;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;
import pl.projectshoes.product.enums.ShoeColor;
import pl.projectshoes.product.enums.Standard;
import pl.projectshoes.product.model.Product;
import pl.projectshoes.product.model.Size;
import pl.projectshoes.product.repository.ProductRepository;
import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.model.ShopUserRole;
import pl.projectshoes.user.repository.ShopUserRepository;
import pl.projectshoes.user.repository.UserRoleRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;


@RequiredArgsConstructor
@Component
public class DebugClass implements CommandLineRunner {

    private final ShopUserRepository shopUserRepository;
    private final ProductRepository productRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) {
        System.out.println(LocalTime.now().toString());


        if(productRepository.findAll().isEmpty()){
            productRepository.save(new Product(Category.KIDS, Brand.ADIDAS,"XP", ShoeColor.WHITE, new Size(30.5,Standard.CM),2,"xd",1,"xddd","img.url",true,true,true,true,true,LocalDateTime.now()));
        }
        if(!userRoleRepository.existsUserRoleByName("USER")){
            userRoleRepository.save(new ShopUserRole("USER","READ_USER"));
            userRoleRepository.save(new ShopUserRole("ADMIN","READ_ADMIN"));
        }
        if(!shopUserRepository.existsByEmail("elo@elo.com")) {

            final ShopUser shopUser = new ShopUser("elo", "elo2", "elo@elo.com", passwordEncoder.encode("xddd"), "512321231", true, true, true, LocalDateTime.now(), Set.of(userRoleRepository.getUserRoleByName("ADMIN")));


            shopUserRepository.save(shopUser);
        }

    }
}
