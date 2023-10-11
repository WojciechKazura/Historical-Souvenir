package com.souvenire.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "admin")
    private boolean admin;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.admin = false;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        if (admin) {
            return "admin";
        } else {
            return "user";
        }
    }

}
