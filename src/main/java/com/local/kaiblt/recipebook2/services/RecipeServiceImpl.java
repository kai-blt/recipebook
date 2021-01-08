package com.local.kaiblt.recipebook2.services;

import com.local.kaiblt.recipebook2.models.Recipe;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService{

    @Transactional
    @Override
    public Recipe save(Recipe recipe) {
        return null;
    }
}
