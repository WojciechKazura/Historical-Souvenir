package com.souvenire.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");//plik html
        String description = "Strona przechowuje informacje o pamiatkach historycznych.";
        boolean isAdmin = isAdmin();
        System.out.println("Czy admin "+isAdmin);
        modelAndView.addObject("description", description);
        modelAndView.addObject("isAdmin", isAdmin );
        return modelAndView;
    }

    boolean isAdmin() {
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


}
