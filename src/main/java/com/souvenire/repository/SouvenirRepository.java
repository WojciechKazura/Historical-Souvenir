package com.souvenire.repository;

import com.souvenire.entity.Souvenir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SouvenirRepository extends JpaRepository<Souvenir, Integer> {

    //1. wykorzystanie
    @Query("SELECT s FROM Souvenir s WHERE (:year IS NULL OR s.souvenirYear = :year)")
// s.x to pole w klasie a jak jest :x to parametr metody
    List<Souvenir> findBySouvenirYear(Integer year);

    //zrobić wersję obsługującą wszystkie 4 parametry wyszukiwania i również doczytać o Specifications API
    @Query("SELECT s FROM Souvenir s WHERE (s.accepted =true)")
    List<Souvenir> findByAccepted();

    @Query("SELECT s FROM Souvenir s WHERE (s.accepted = false)")
    List<Souvenir> findUnAccepted();


}
