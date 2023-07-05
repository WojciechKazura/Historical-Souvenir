package com.souvenire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class TextController {

    @GetMapping("/articles")
    public String showText(Model model) throws IOException {
        // Wczytaj zawartość pliku tekstowego
        Path filePath = Paths.get("src/main/resources/static/articles/article1.txt");
        String text = new String(Files.readAllBytes(filePath));

        // Przekaż tekst do modelu
        model.addAttribute("text", text);

        // Zwróć nazwę szablonu Thymeleaf
        return "text";
    }








}
