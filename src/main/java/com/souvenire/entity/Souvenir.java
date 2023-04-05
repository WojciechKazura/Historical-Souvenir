package com.souvenire.entity;

import jakarta.persistence.*;

@Entity
@Table(name="souvenirs")
public class Souvenir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name="souvenir_year")
    private int souvenirYear;
    @Column(name="category")
    private String category;
    @Column(name="historical_period")
    private String historicalPeriod;

    public Souvenir(String name, int year, String category, String historicalPeriod) {
        this.name = name;
        this.souvenirYear = year;
        this.category = category;
        this.historicalPeriod = historicalPeriod;
    }


     Souvenir() {
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", souvenirYear=" + souvenirYear +
                ", category='" + category + '\'' +
                ", historicalPeriod='" + historicalPeriod + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getSouvenirYear() {
        return souvenirYear;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getHistoricalPeriod() {
        return historicalPeriod;
    }
}
