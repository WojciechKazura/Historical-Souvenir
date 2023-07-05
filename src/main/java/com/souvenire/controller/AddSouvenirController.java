package com.souvenire.controller;

import com.souvenire.service.SouvenirService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Controller
public class AddSouvenirController {

    private SouvenirService service;


    public AddSouvenirController(SouvenirService service) {
        this.service = service;
    }

  ;

    @RequestMapping(path="/add",method = RequestMethod.GET)
    ModelAndView addSouvenirForm(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("add.html");//plik html
        return modelAndView;
    }

    @PostMapping("/add")
    ModelAndView addSouvenir(String name, Integer year, String category, String historicalPeriod,@RequestParam("image") MultipartFile imageFile) throws IOException {
        System.out.println(imageFile.getName());
        System.out.println(imageFile.getOriginalFilename());
        System.out.println(imageFile.isEmpty());
        service.addSouvenir(name, year, category,historicalPeriod, imageFile);
        ModelAndView modelAndView =new ModelAndView("home");
        modelAndView.addObject("description","Dodano poprawnie pamiątkę");
        return modelAndView;
    }






}
