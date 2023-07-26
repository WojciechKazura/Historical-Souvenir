package com.souvenire.service;


import com.souvenire.entity.Scenario;
import com.souvenire.repository.ScenarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {

    private ScenarioRepository scenarioRepository;

    public ScenarioService(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    public void addScenario(String name, String description) {
        Scenario scenario = new Scenario(name, description);
        scenarioRepository.save(scenario);
        //System.out.println(scenario);
    }


}
