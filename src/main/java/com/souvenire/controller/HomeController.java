package com.souvenire.controller;


import com.souvenire.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");//plik html
        boolean isAdmin = userService.isAdmin();
        System.out.println("Czy admin "+isAdmin);
        modelAndView.addObject("isAdmin", isAdmin );
        String userName=userService.getUserName();
        if(!userName.isEmpty()){
            modelAndView.addObject("userName",userName);
        }
        return modelAndView;
    }




}
