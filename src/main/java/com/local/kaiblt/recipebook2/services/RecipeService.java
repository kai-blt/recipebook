package com.local.kaiblt.recipebook2.services;

import com.local.kaiblt.recipebook2.models.Recipe;
import com.local.kaiblt.recipebook2.models.Role;
import com.local.kaiblt.recipebook2.models.User;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    Recipe findRecipeById(long id);

    Recipe save(Recipe recipe);

    Recipe update(Recipe recipe, long id);

    void delete(long id);
}
