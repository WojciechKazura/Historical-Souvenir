package com.souvenire.controller;


import com.souvenire.service.ScenarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class AddScenarioController {

    private ScenarioService service;

    public AddScenarioController(ScenarioService service) {
        this.service = service;
    }

    @GetMapping(path="/add-scenario")
    ModelAndView addScenarioForm(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("add-scenario.html");//plik html
        return modelAndView;
    }

    @PostMapping("/add-scenario")
    ModelAndView addScenario(String name,   String description)  throws IOException {
        service.addScenario(name, description);
        ModelAndView modelAndView =new ModelAndView("home");
        modelAndView.addObject("description","Dodano poprawnie pamiątkę");
        return modelAndView;
    }




}
