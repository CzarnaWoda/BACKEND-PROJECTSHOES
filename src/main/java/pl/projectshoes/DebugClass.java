package pl.projectshoes;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.repository.ShopUserRepository;

import java.time.LocalDateTime;


@RequiredArgsConstructor
public class DebugClass implements CommandLineRunner {

    private final ShopUserRepository shopUserRepository;
    @Override
    public void run(String... args) throws Exception {
        final ShopUser shopUser = new ShopUser("elo","elo2","elo@elo.com","xddd","512321231",true,true,true, LocalDateTime.now());

        shopUserRepository.save(shopUser);

    }
}
