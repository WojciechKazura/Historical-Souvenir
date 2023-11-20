package com.souvenire.controller;

import com.souvenire.entity.Souvenir;
import com.souvenire.service.SouvenirService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewSouvenirController {

    private SouvenirService service;

    //@RequestMapping(path="/view",method = RequestMethod.GET)
   /* @GetMapping("/view")
    ModelAndView getHomePage(){
        return  new ModelAndView("view-souvenirs.html");
    }*/

    public ViewSouvenirController(SouvenirService service) {
        this.service = service;
    }

    @GetMapping("/view-souvenirs")
    ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("view-souvenirs");
        List<Souvenir> souvenirList = service.getAcceptedSouvenirs();
        modelAndView.addObject("souvenirs", souvenirList);
        return modelAndView;
    }

    @PostMapping("/view-souvenirs")
    ModelAndView searchSouvenir(String name, Integer year, String category, String period) { //skad ten przecinek?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ModelAndView modelAndView = new ModelAndView("view-souvenirs");
        List<Souvenir> souvenirList = service.findByParameters(name, year, category, period);
        modelAndView.addObject("souvenirs", souvenirList);
        return modelAndView;
    }
    @GetMapping("/admin")
    ModelAndView viewSouvenir() { //skad ten przecinek?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ModelAndView modelAndView = new ModelAndView("admin");
        List<Souvenir> souvenirList = service.getUnAcceptedSouvenirs();
        modelAndView.addObject("souvenirs", souvenirList);
        return modelAndView;
    }

}
