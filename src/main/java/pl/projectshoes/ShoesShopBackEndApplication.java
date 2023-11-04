package pl.projectshoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import pl.projectshoes.security.config.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ShoesShopBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoesShopBackEndApplication.class, args);
    }


}
