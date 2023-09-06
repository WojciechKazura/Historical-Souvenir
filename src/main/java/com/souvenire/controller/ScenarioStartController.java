package com.souvenire.controller;

import com.souvenire.entity.Scenario;
import com.souvenire.entity.Souvenir;
import com.souvenire.service.ScenarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ScenarioStartController {

    private ScenarioService scenarioService;

    public ScenarioStartController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @PostMapping("/scenario-start")
    ModelAndView start(int scenarioID,int souvenirIndex) {
        System.out.println("Start"+ scenarioID);
        ModelAndView modelAndView = new ModelAndView("scenario-start");
        Scenario scenario = scenarioService.getScenario(scenarioID);
        Souvenir souvenir = scenario.getSouvenirList().get(souvenirIndex);
        modelAndView.addObject("souvenir",souvenir);
        modelAndView.addObject("scenario",scenario);
        modelAndView.addObject("souvenirIndex",souvenirIndex);
        return modelAndView;
    }




}
