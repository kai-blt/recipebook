package com.local.kaiblt.recipebook2.services;

import com.local.kaiblt.recipebook2.models.Ingredient;
import com.local.kaiblt.recipebook2.models.Recipe;
import com.local.kaiblt.recipebook2.models.Step;
import com.local.kaiblt.recipebook2.models.User;
import com.local.kaiblt.recipebook2.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Locale;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    UserService userService;

    @Autowired
    RecipeRepository reciperepos;

    @Transactional
    @Override
    public Recipe save(Recipe recipe) {

        Recipe newRecipe = new Recipe();

        newRecipe.setName(recipe.getName());
        newRecipe.setType(recipe.getType());
        newRecipe.setUser(userService.findByUsername(recipe.getUser().getUsername()));

        //Add all ingredients to the recipe
        for (Ingredient i : recipe.getIngredients()) {
            newRecipe.getIngredients().add(new Ingredient(i.getName(), i.getQuantity(), i.getMeasurement(), newRecipe));
        }

        //Add all steps to the recipe
        for (Step s : recipe.getSteps()) {
            newRecipe.getSteps().add(new Step(s.getStepnumber(), s.getInstructions(), newRecipe));
        }

        return reciperepos.save(newRecipe);
    }
}
