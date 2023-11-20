package com.souvenire.repository;

import com.souvenire.entity.Souvenir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SouvenirRepository extends JpaRepository<Souvenir, Integer> {

    //1. wykorzystanie
    // s.x to pole w klasie a jak jest :x to parametr metody
    // : każe szukać w parametrach
    @Query("SELECT s FROM Souvenir s WHERE (s.accepted = :accepted AND s.name = :name)")
    List<Souvenir> findBySouvenirForName(String name,boolean accepted);

    @Query("SELECT s FROM Souvenir s WHERE (s.accepted = :accepted AND s.souvenirYear = :year)")
    List<Souvenir> findBySouvenirYear(Integer year, boolean accepted);

    //@Query("SELECT s FROM Souvenir s WHERE (s.accepted = :accepted AND s.category = :category)")
    //List<Souvenir> findBySouvenirParametersForCategory(String category);

    @Query("SELECT s FROM Souvenir s WHERE (s.accepted = :accepted AND s.historicalPeriod = :period)")
    List<Souvenir> findBySouvenirParametersForHistoricalPeriod(String period,boolean accepted);

    @Query("SELECT s FROM Souvenir s WHERE (s.accepted = :accepted AND s.name = :name AND s.souvenirYear = :year AND s.category = :category AND s.historicalPeriod =:period )")
    List<Souvenir> findBySouvenirParameters(String name, Integer year, String category, String period, boolean accepted);


    //@Query("SELECT s FROM Souvenir s WHERE (s.accepted = :accepted AND s.name = :name)")
    //List<Souvenir> findByParameters(boolean accepted, String name, Integer year, String category, String period);

    //zrobić wersję obsługującą wszystkie 4 parametry wyszukiwania i również doczytać o Specifications API
   /* @Query("SELECT s FROM Souvenir s WHERE (s.accepted =true)")
    List<Souvenir> findByAccepted();

    @Query("SELECT s FROM Souvenir s WHERE (s.accepted = false)")
    List<Souvenir> findUnAccepted();
*/
    @Query("SELECT s FROM Souvenir s WHERE (s.accepted = :accepted)")
// : każe szukać w parametrach
    List<Souvenir> find(boolean accepted);

  /* default List<Souvenir> find(boolean accepted){
        if(accepted){
            return findByAccepted();
        }else {
           return findUnAccepted();
        }
    }*/


}
