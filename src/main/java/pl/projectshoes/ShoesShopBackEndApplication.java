package pl.projectshoes;

import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.projectshoes.user.model.ShopUser;

import java.time.LocalDateTime;

@SpringBootApplication
public class ShoesShopBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoesShopBackEndApplication.class, args);
    }


}
