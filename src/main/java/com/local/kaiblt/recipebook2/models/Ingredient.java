package com.local.kaiblt.recipebook2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    //--------Ingredient Primary Key--------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientid;

    //--------Ingredient Fields--------
    @NotNull
    private String name;

    private String quantity;

    private String measurement;

    private String ingredientgroup;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private Recipe recipe;


    //--------Constructors--------
    public Ingredient() {
    }

    public Ingredient(String name, String quantity, String measurement, String ingredientgroup, Recipe recipe) {
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
        this.ingredientgroup = ingredientgroup;
        this.recipe = recipe;
    }

    //--------Getters & Setters--------
    public long getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(long ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getIngredientgroup() {
        return ingredientgroup;
    }

    public void setIngredientgroup(String ingredientgroup) {
        this.ingredientgroup = ingredientgroup;
    }
}
