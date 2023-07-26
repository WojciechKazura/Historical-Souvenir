package com.souvenire.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table (name="scenarios")
public class Scenario {

    @Id
    private int id;

    private String name;

    public String getDescription() {
        return description;
    }

    @ManyToMany
    private List<Souvenir> souvenirList;

    private String description;






}
