package com.seis739.AbsoluteUnit.model;

import jakarta.persistence.*;

import java.io.Serializable;

//change class initializers
@Entity
public class Favorites implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String type;
    private String muscle;
    private String equipment;
    private String difficulty;
//    @Column(columnDefinition = "TEXT")
//    private String instructions;

    public Favorites() {
    }

    public Favorites(Long id, String name, String type, String muscle, String equipment, String difficulty, String instructions) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.muscle = muscle;
        this.equipment = equipment;
        this.difficulty = difficulty;
//        this.instructions = instructions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String phone) {
        this.equipment = equipment;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

//    public String getInstructions() {
//        return instructions;
//    }

//    public void setInstructions(String instructions) {
//        this.instructions = instructions;
//    }

    @Override
    public String toString() {
        return "Favorites{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", muscle='" + muscle + '\'' +
                ", equipment='" + equipment + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", instructions='" +  + '\'' +
                '}';
    }
}
