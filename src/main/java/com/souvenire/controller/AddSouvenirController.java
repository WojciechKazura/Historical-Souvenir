package com.souvenire.controller;

import com.souvenire.service.SouvenirService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddSouvenirController {

    private SouvenirService service;

    public AddSouvenirController(SouvenirService service) {
        this.service = service;
    }

    @RequestMapping(path="/add",method = RequestMethod.GET)
    ModelAndView addSouvenirForm(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("add.html");//plik html
        return modelAndView;
    }

    @PostMapping("/add")
    String addSouvenir(String name, int year, String category, String historicalPeriod){
        service.addSouvenir(name, year, category,historicalPeriod);
        return "add-result";
    }




}
