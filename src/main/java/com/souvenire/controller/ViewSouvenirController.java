package com.souvenire.controller;

import com.souvenire.entity.Souvenir;
import com.souvenire.service.SouvenirService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewSouvenirController {

    private SouvenirService service;

    //@RequestMapping(path="/view",method = RequestMethod.GET)
   /* @GetMapping("/view")
    ModelAndView getHomePage(){
        return  new ModelAndView("view.html");
    }*/

    public ViewSouvenirController(SouvenirService service) {
        this.service = service;
    }

    @GetMapping("/view")
    ModelAndView getHomePage(){
        ModelAndView modelAndView= new ModelAndView();
        List<Souvenir> souvenirList=service.getSouvenirs();
        modelAndView.addObject("souvenir",souvenirList.get(0));
        return modelAndView;
    }

}
