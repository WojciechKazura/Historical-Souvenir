package com.souvenire.repository;

import com.souvenire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, String> {

    List<User> findAllByAdmin(boolean admin);

}
