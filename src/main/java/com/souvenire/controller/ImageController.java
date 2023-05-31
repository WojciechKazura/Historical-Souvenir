package com.souvenire.controller;


import com.souvenire.service.SouvenirService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {



        @GetMapping("/images/{imageName}")
        @ResponseBody
        public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
            Path imagePath = Paths.get(SouvenirService.UPLOAD_DIRECTORY, imageName);
            Resource imageResource;
            try {
                imageResource = new UrlResource(imagePath.toUri());
            } catch (MalformedURLException e) {
                // Obsługa błędu
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageResource);
        }
    }

