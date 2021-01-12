package com.local.kaiblt.recipebook2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    //--------Recipe Primary Key--------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;


    //--------Recipe Fields--------
    @NotNull
    private String name;

    @NotNull
    private String type;

    private String imageURL;

    //ManyToOne - > Recipe
    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private User user;

    //OneToMany -> Ingredient
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private List<Ingredient> ingredients = new ArrayList<>();

    //OneToMany -> Step
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private List<Step> steps = new ArrayList<>();



    //--------Constructors--------
    public Recipe() {
    }

    public Recipe(String name, String type, String imageURL, User user) {
        this.name = name;
        this.type = type;
        this.imageURL = imageURL;
        this.user = user;
    }

    //--------Getters & Setters--------
    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
