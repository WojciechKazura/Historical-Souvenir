package com.souvenire.controller;


import com.souvenire.entity.Scenario;
import com.souvenire.entity.Souvenir;
import com.souvenire.service.ScenarioService;
import com.souvenire.service.SouvenirService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class ScenarioController {

    private ScenarioService scenarioService;
    private SouvenirService souvenirService;


    public ScenarioController(ScenarioService scenarioService, SouvenirService souvenirService) {
        this.scenarioService = scenarioService;
        this.souvenirService = souvenirService;
    }

    @GetMapping(path = "/add-scenario")
    ModelAndView addScenarioForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-scenario.html");//plik html
        return modelAndView;
    }

    @PostMapping("/add-scenario")
    ModelAndView addScenario(String name, String description) throws IOException {
        Scenario scenario = scenarioService.addScenario(name, description);
        return addSouvenireToScenarioPage(scenario);
    }

    ModelAndView addSouvenireToScenarioPage(Scenario scenario) {
        ModelAndView modelAndView = new ModelAndView("add-souvenir-to-scenario");
        modelAndView.addObject("scenario", scenario);
        List<Souvenir> souvenirList = souvenirService.getAcceptedSouvenirs();
        modelAndView.addObject("souvenirs", souvenirList);
        return modelAndView;
    }

    @PostMapping("/add-souvenir-to-scenario")
    ModelAndView addSouvenirToScenario(int scenarioID,int souvenirID) {
        scenarioService.addSouvenir(scenarioID,souvenirID);
        Scenario scenario =  scenarioService.getScenario(scenarioID);
        return addSouvenireToScenarioPage(scenario);
    }


}
