package com.local.kaiblt.recipebook2.services;

import com.local.kaiblt.recipebook2.models.Ingredient;
import com.local.kaiblt.recipebook2.models.Recipe;
import com.local.kaiblt.recipebook2.models.Step;
import com.local.kaiblt.recipebook2.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    UserService userService;

    @Autowired
    RecipeRepository reciperepos;

    @Override
    public List<Recipe> findAll() {

        List<Recipe> recipeList = new ArrayList<>();

        reciperepos.findAll()
                .iterator().forEachRemaining(recipeList::add);

        return recipeList;
    }

    @Override
    public Recipe findRecipeById(long id) {
        return reciperepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe Not Found"));
    }

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

    @Transactional
    @Override
    public Recipe update(Recipe recipe, long id) {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (reciperepos.findById(id).isPresent()) {
            reciperepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Recipe Not Found");
        }
    }
}
