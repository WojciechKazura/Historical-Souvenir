package com.souvenire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(path="/",method = RequestMethod.GET)
    ModelAndView getHomePage(){
        System.out.println("Witam!");
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("home.html");//plik html
        String description ="Strona przechowuje informacje o pamiatkach historycznych.";
        modelAndView.addObject("description",description);
        return modelAndView;
    }





}
