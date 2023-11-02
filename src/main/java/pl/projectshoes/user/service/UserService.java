package pl.projectshoes.user.service;


import org.springframework.stereotype.Service;
import pl.projectshoes.user.UserRepository;
import pl.projectshoes.user.model.User;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserExist(String email){
        return userRepository.existsByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
