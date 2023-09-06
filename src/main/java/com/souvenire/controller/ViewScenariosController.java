package com.souvenire.controller;

import com.souvenire.entity.Scenario;
import com.souvenire.service.ScenarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewScenariosController {

    private ScenarioService scenarioService;

    public ViewScenariosController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @GetMapping("/view-scenarios")
    ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("view-scenarios");
        List<Scenario> scenarioList = scenarioService.getAllScenario();
        modelAndView.addObject("scenarios", scenarioList);
        return modelAndView;
    }






   /* @PostMapping("/view")
        ModelAndView searchSouvenir(String name, Integer year, String category, String period) { //skad ten przecinek?
        List<Souvenir> souvenirList = service.findByParameters(name, year, category, period);
        modelAndView.addObject("souvenirs", souvenirList);
        return modelAndView;
    }*/

}
