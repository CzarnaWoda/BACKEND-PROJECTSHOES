package pl.projectshoes.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projectshoes.user.repository.UserRepository;
import pl.projectshoes.user.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public boolean isUserExist(String email){
        return userRepository.existsByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
