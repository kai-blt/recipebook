package com.local.kaiblt.recipebook2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "steps")
public class Step {
    //--------Step Primary Key--------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stepid;

    //--------Step Fields--------
    @NotNull
    private long stepnumber;

    @NotNull
    @Column(length = 1500)
    @Size(min = 1, max = 1500)
    private String instructions;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "steps", allowSetters = true)
    private Recipe recipe;

    //--------Constructors--------
    public Step() {
    }

    public Step(long stepnumber, String instructions, Recipe recipe) {
        this.stepnumber = stepnumber;
        this.instructions = instructions;
        this.recipe = recipe;
    }

    //--------Getters & Setters--------
    public long getStepid() {
        return stepid;
    }

    public void setStepid(long stepid) {
        this.stepid = stepid;
    }

    public long getStepnumber() {
        return stepnumber;
    }

    public void setStepnumber(long stepnumber) {
        this.stepnumber = stepnumber;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
