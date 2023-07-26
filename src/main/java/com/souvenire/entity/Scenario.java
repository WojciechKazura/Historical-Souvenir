package com.souvenire.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "scenarios")
public class Scenario {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String description;

    @ManyToMany
    private List<Souvenir> souvenirList;

    public Scenario( String name, String description) {
        this.name = name;
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", souvenirList=" + souvenirList +
                '}';
    }
}
