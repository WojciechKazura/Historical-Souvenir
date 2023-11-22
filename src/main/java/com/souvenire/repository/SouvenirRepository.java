package com.souvenire.repository;

import com.souvenire.entity.Souvenir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SouvenirRepository extends JpaRepository<Souvenir, Integer> {
    //1. wykorzystanie
    // s.x to pole w klasie a jak jest :x to parametr metody
    // : każe szukać w parametrach
    @Query("SELECT s FROM Souvenir s WHERE (:name IS NULL OR s.name = :name) AND (:souvenirYear IS NULL OR s.souvenirYear = :souvenirYear) AND (:category IS NULL OR s.category = :category) AND (:historicalPeriod IS NULL OR s.historicalPeriod = :historicalPeriod) AND (:accepted IS NULL OR s.accepted = :accepted)")
    List<Souvenir> findSouvenirs(String name, Integer souvenirYear,  String category,  String historicalPeriod,  Boolean accepted);

    @Query("SELECT s FROM Souvenir s WHERE (s.accepted = :accepted)")
    List<Souvenir> find(boolean accepted);




}
