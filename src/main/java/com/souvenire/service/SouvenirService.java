package com.souvenire.service;

import com.souvenire.entity.Souvenir;
import com.souvenire.repository.SouvenirRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SouvenirService {

    private SouvenirRepository souvenirRepository;

    public SouvenirService(SouvenirRepository souvenirRepository) {
        this.souvenirRepository = souvenirRepository;
    }
    public void addSouvenir(String name, int year, String category, String historicalPeriod) {
        System.out.println("Dodaje pamiątkę " + name + " z roku " + year + " z kategori "+category+" z okresu "+ historicalPeriod+" .");
        Souvenir souvenir = new Souvenir(name, year, category, historicalPeriod);
        souvenirRepository.save(souvenir);
    }
    public List<Souvenir> getSouvenirs(){
        return souvenirRepository.findAll();
    }


}
