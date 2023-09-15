package com.souvenire.controller;


import com.souvenire.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class AddUserController {

    private UserService service;

    public AddUserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(path="/add-user",method = RequestMethod.GET)
    ModelAndView addUserForm(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("add-user.html");//plik html
        return modelAndView;
    }

    @PostMapping("/add-user")
    ModelAndView addUser(String login, String password) throws IOException {
        service.addUser(login, password);
        ModelAndView modelAndView =new ModelAndView("add-user");
        modelAndView.addObject("description","Dodano poprawnie urzytkownika");
        return modelAndView;
    }



}
