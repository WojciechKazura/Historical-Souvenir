package com.souvenire.service;

import com.souvenire.entity.Souvenir;
import com.souvenire.repository.SouvenirRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class SouvenirService {

    public static final String UPLOAD_DIRECTORY ="src/main/resources/static/upload";
    private SouvenirRepository souvenirRepository;

    public SouvenirService(SouvenirRepository souvenirRepository) {
        this.souvenirRepository = souvenirRepository;
    }

    public void addSouvenir(String name, int year, String category, String historicalPeriod, MultipartFile imageFile) throws IOException {
        System.out.println("Dodaje pamiątkę " + name + " z roku " + year + " z kategori " + category + " z okresu " + historicalPeriod + " .");
        Souvenir souvenir = new Souvenir(name, year, category, historicalPeriod);
        souvenirRepository.save(souvenir);
        boolean isImage=imageFile.isEmpty();
        saveImage(isImage,imageFile, souvenir);
        // Pobierz nazwę pliku

    }

    public List<Souvenir> getSouvenirs() {
        return souvenirRepository.findAll();
    }

    public List<Souvenir> findByCategory(String category) {
        List<Souvenir> souvenirList = getSouvenirs();
        List<Souvenir> filterList = new ArrayList<>();
        for (Souvenir souvenir : souvenirList) {
            if (souvenir.getCategory().equals(category)) {
                filterList.add(souvenir);
            }
        }
        return filterList;
    }

    private void saveImage(boolean isImage, MultipartFile imageFile, Souvenir souvenir)throws IOException{
        if(!isImage){
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            String string[]=fileName.split("\\.");
            String newName=souvenir.getId()+"."+string[1];
            souvenir.setImageName(newName);
            souvenirRepository.save(souvenir);
            System.out.println(newName);
            // Określ ścieżkę, w której zostanie zapisany plik
            Path uploadDir = Paths.get(UPLOAD_DIRECTORY);
            Path filePath = uploadDir.resolve(newName);
            System.out.println(filePath);
            // Zapisz plik na dysku
            Files.createDirectories(uploadDir);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            // Zapisz informacje o pliku w bazie danych
        }else{
            String newName="empty.jpg";
            souvenir.setImageName(newName);
            souvenirRepository.save(souvenir);
            System.out.println(newName);
            // Określ ścieżkę, w której zostanie zapisany plik
            Path uploadDir = Paths.get(UPLOAD_DIRECTORY);
            Path filePath = uploadDir.resolve(newName);
            System.out.println(filePath);
            // Zapisz informacje o pliku w bazie danych
        }



    }

}
