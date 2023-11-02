package pl.projectshoes;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.projectshoes.user.model.ShopUser;
import pl.projectshoes.user.repository.ShopUserRepository;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Component
public class DebugClass implements CommandLineRunner {

    private final ShopUserRepository shopUserRepository;
    @Override
    public void run(String... args) {
        if(!shopUserRepository.existsByEmail("elo@elo.com")) {
            final ShopUser shopUser = new ShopUser("elo", "elo2", "elo@elo.com", "xddd", "512321231", true, true, true, LocalDateTime.now());

            shopUserRepository.save(shopUser);
        }

    }
}
