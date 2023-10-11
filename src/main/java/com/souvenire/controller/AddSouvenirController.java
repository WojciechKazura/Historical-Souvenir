package com.souvenire.controller;

import com.souvenire.service.SouvenirService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Controller
public class AddSouvenirController {

    private SouvenirService service;

    public AddSouvenirController(SouvenirService service) {
        this.service = service;
    }

    @RequestMapping(path="/add-souvenir",method = RequestMethod.GET)
    ModelAndView addSouvenirForm(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("add-souvenir.html");//plik html
        return modelAndView;
    }

    @PostMapping("/add-souvenir")
    ModelAndView addSouvenir(String name, Integer year, String category, String historicalPeriod,@RequestParam("image") MultipartFile imageFile, String article) throws IOException {
        service.addSouvenir(name, year, category,historicalPeriod, imageFile, article);
        ModelAndView modelAndView =new ModelAndView("home");
        modelAndView.addObject("description","Dodano poprawnie pamiątkę");
        return modelAndView;
    }

    @PostMapping("/admin")
    ModelAndView acceptSouvenir(int souvenirID){
        ModelAndView modelAndView= new ModelAndView("admin");
        service.acceptSouvenir(souvenirID);
        return modelAndView;
    }






}
