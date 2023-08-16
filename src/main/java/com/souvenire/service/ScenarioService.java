package com.souvenire.service;


import com.souvenire.entity.Scenario;
import com.souvenire.entity.Souvenir;
import com.souvenire.repository.ScenarioRepository;
import com.souvenire.repository.SouvenirRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioService {

    private ScenarioRepository scenarioRepository;
    private SouvenirRepository souvenirRepository;

    public ScenarioService(ScenarioRepository scenarioRepository, SouvenirRepository souvenirRepository) {
        this.scenarioRepository = scenarioRepository;
        this.souvenirRepository = souvenirRepository;
    }

    public Scenario addScenario(String name, String description) {
        Scenario scenario = new Scenario(name, description);
        scenarioRepository.save(scenario);
        return scenario;
    }

    public void addSouvenir(int scenarioId,int souvenirId){
        Scenario scenario=scenarioRepository.findById(scenarioId).orElseThrow();
        Souvenir souvenir=souvenirRepository.findById(souvenirId).orElseThrow();
        scenario.addSouvenir(souvenir);
        scenarioRepository.save(scenario);
    }


    public Scenario getScenario(int scenarioID) {
       return scenarioRepository.findById(scenarioID).orElseThrow();
    }

    public List<Scenario> getAllScenario(){
        return scenarioRepository.findAll();
    }
}