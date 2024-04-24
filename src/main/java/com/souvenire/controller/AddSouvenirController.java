package com.souvenire.controller;

import com.souvenire.service.SouvenirService;
import com.souvenire.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class AddSouvenirController {

    private SouvenirService service;
    private UserService userService;

    public AddSouvenirController(SouvenirService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @RequestMapping(path = "/add-souvenir", method = RequestMethod.GET)
    ModelAndView addSouvenirForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-souvenir.html");//plik html
        return modelAndView;
    }

    @PostMapping("/add-souvenir")
    ModelAndView addSouvenir(String name, Integer year, String category, String historicalPeriod,
                             @RequestParam("image") MultipartFile imageFile, String article,
                             RedirectAttributes redirectAttributes) throws IOException {
        service.addSouvenir(name, year, category, historicalPeriod, imageFile, article);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        redirectAttributes.addFlashAttribute("message", "Dodano poprawnie pamiątkę, po akceptacji przez administratora będzie widoczna.");
        return modelAndView;
    }

    @PostMapping("/admin")
    ModelAndView acceptSouvenir(int souvenirID) {
        ModelAndView modelAndView = new ModelAndView("admin");
        service.acceptSouvenir(souvenirID);
        return modelAndView;
    }


}
