package com.souvenire.service;



import com.souvenire.entity.User;
import com.souvenire.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class UserService {

    private UserRepository userRepository;

     UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void addUser(String login, String password) throws IOException {
        User user = new User(login, password);
        userRepository.save(user);

    }

}
