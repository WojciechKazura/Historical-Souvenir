package com.souvenire.service;

import com.souvenire.entity.Souvenir;
import com.souvenire.repository.SouvenirRepository;
import jakarta.transaction.Transactional;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SouvenirService {

    public static final String UPLOAD_DIRECTORY = "src/main/resources/static/upload";
    private SouvenirRepository souvenirRepository;

    public SouvenirService(SouvenirRepository souvenirRepository) {
        this.souvenirRepository = souvenirRepository;
    }

    public void addSouvenir(String name, Integer year, String category, String historicalPeriod, MultipartFile imageFile, String article) throws IOException {
        System.out.println("Dodaje pamiątkę " + name + " z roku " + year + " z kategori " + category + " z okresu " + historicalPeriod + " ." + "atukuł " + article);
        Souvenir souvenir = new Souvenir(name, year, category, historicalPeriod, article);
        souvenirRepository.save(souvenir);
        boolean isImage = imageFile.isEmpty();
        saveImage(isImage, imageFile, souvenir);
        // Pobierz nazwę pliku

    }

    public List<Souvenir> getAcceptedSouvenirs() {
        return souvenirRepository.find(true);
    }

    public List<Souvenir> getAllSouvenirs() {
        return souvenirRepository.findAll();
    }

    public List<Souvenir> getUnAcceptedSouvenirs(){
        return souvenirRepository.find(false);
    }

    public List<Souvenir> findByCategory(String category) {
        List<Souvenir> souvenirList = getAcceptedSouvenirs();
        List<Souvenir> filterList = new ArrayList<>();
        for (Souvenir souvenir : souvenirList) {
            if (souvenir.getCategory().equals(category)) {
                filterList.add(souvenir);
            }
        }
        return filterList;
    }

    public List<Souvenir> findByParameters(String name, Integer year, String category, String period) {
        List<Souvenir> souvenirList = souvenirRepository.findBySouvenirYear(year);

        return souvenirList;
    }

   /* public List<Souvenir> findByParameters(String name, Integer year, String category, String period) {
        List<Souvenir> souvenirList = getSouvenirs();
        List<Souvenir> filterList = new ArrayList<>();
        for (Souvenir souvenir : souvenirList) {
           boolean nameCorrect = name == null || souvenir.getName().equals(name);
           boolean yearCorrect = year == null || souvenir.getSouvenirYear() == year;
           boolean categoryCorrect = category == null || souvenir.getCategory().equals(category);
           boolean periodCorrect = period == null || souvenir.getHistoricalPeriod().equals(period);
            if (nameCorrect && yearCorrect && categoryCorrect && periodCorrect) {
                filterList.add(souvenir);
            }
        }

        return filterList;
    }*/

    private void saveImage(boolean isImage, MultipartFile imageFile, Souvenir souvenir) throws IOException {
        if (!isImage) {
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            String string[] = fileName.split("\\.");
            String newName = souvenir.getId() + "." + string[1];
            souvenir.setImageName(newName);
            souvenirRepository.save(souvenir);
            System.out.println(newName);
            // Określ ścieżkę, w której zostanie zapisany plik
            Path uploadDir = Paths.get(UPLOAD_DIRECTORY);
            Path filePath = uploadDir.resolve(newName);
            System.out.println(filePath);
            // Zapisz plik na dysku
            Files.createDirectories(uploadDir);
            // Wymiary docelowego obrazka
            int targetWidth = 800;
            int targetHeight = 600;
            // Oblicz proporcjonalne wymiary zmniejszonego obrazka
            Thumbnails.Builder<? extends InputStream> thumbnailBuilder = Thumbnails.of(imageFile.getInputStream());
            thumbnailBuilder.size(targetWidth, targetHeight);
            thumbnailBuilder.keepAspectRatio(true);
            thumbnailBuilder.toFile(filePath.toFile());
            // Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            // Zapisz informacje o pliku w bazie danych
        } else {
            String newName = "empty.jpg";
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

    @Transactional // modyfikacje obiektów objęte tranzakcja są modyfikowane w bazie danych
    public void acceptSouvenir(int souvenirID) {
        souvenirRepository.findById(souvenirID).orElseThrow().setAccepted(true);
    }


}
