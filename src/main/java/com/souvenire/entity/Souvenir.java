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
    @Column(name="image_name")
    private String imageName;

    @Column(name="article")
    @Lob
    private String article;

    @Column(name= "accepted")
    private boolean accepted;

    public Souvenir(String name, int year, String category, String historicalPeriod, String article) {
        this.name = name;
        this.souvenirYear = year;
        this.category = category;
        this.historicalPeriod = historicalPeriod;
        this.article = article;
        accepted=false;
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
                ", imageName='" + imageName + '\'' +
                ", article='" + article + '\'' +
                ", accepted=" + accepted +
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getArticle() {
        return article;
    }
    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
