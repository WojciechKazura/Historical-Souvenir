package com.souvenire.service;


import com.souvenire.entity.User;
import com.souvenire.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostConstruct
    public void createAdminIfNotExist() {
        if (userRepository.findAllByAdmin(true).isEmpty()) {
            User user = new User("admin", passwordEncoder.encode("admin"));
            user.setAdmin(true);
            userRepository.save(user);
        }
    }

    public void addUser(String login, String password) throws IOException {
        User user = new User(login, passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
                    .password(userOptional.get().getPassword())
                    .roles(userOptional.get().getRole())
                    .build();
            return userDetails;
        }
        throw new UsernameNotFoundException("Nie ma takiego użytkownika.");
    }

    public boolean isAdmin() {
        // Pobierz obiekt Authentication z SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        // Pobierz role użytkownika
        // W przypadku roli, możesz uzyskać dostęp do obiektu GrantedAuthority
        // a następnie pobrać nazwę roli za pomocą metody getAuthority()
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if (role.equals("ROLE_admin")) {
                return true;
            }
        }
        return false;
    }

    private Optional<UserDetails> getLoggingUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user = authentication.getPrincipal();
        if(user instanceof UserDetails){
            return Optional.of((UserDetails)user);
        }
        return Optional.empty();
    }

    public String getUserName() {
        return getLoggingUser().map(user->user.getUsername()).orElse("");
    }
}
