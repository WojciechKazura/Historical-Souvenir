package com.souvenire.service;


import com.souvenire.entity.User;
import com.souvenire.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(String login, String password) throws IOException {
        User user = new User(login, passwordEncoder.encode(password));
        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepository.findById(username);
        if(userOptional.isPresent()){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
                    .password(userOptional.get().getPassword())
                    .roles(userOptional.get().getRole())
                    .build();
            return userDetails;
        }
        throw  new UsernameNotFoundException("Nie ma takiego użytkownika.");
    }
}
