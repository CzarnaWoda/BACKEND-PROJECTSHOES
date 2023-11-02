package pl.projectshoes;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.projectshoes.product.enums.Brand;
import pl.projectshoes.product.enums.Category;
import pl.projectshoes.product.model.Product;
import pl.projectshoes.product.repository.ProductRepository;
import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.repository.ShopUserRepository;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;


@RequiredArgsConstructor
@Component
public class DebugClass implements CommandLineRunner {

    private final ShopUserRepository shopUserRepository;
    private final ProductRepository productRepository;
    @Override
    public void run(String... args) {
        System.out.println(LocalTime.now().toString());


        if(productRepository.findAll().isEmpty()){
            productRepository.save(new Product(Category.KIDS, Brand.ADIDAS,"XP", Color.black,2,2,"xd",1,"xddd","img.url",true,true,true,true,true,LocalDateTime.now()));
        }
        if(!shopUserRepository.existsByEmail("elo@elo.com")) {

            final ShopUser shopUser = new ShopUser("elo", "elo2", "elo@elo.com", "xddd", "512321231", true, true, true, LocalDateTime.now());

            shopUserRepository.save(shopUser);
        }

    }
}
