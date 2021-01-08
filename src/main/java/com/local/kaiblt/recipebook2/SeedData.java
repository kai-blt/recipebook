package com.local.kaiblt.recipebook2;

import com.local.kaiblt.recipebook2.models.*;
import com.local.kaiblt.recipebook2.services.RoleService;
import com.local.kaiblt.recipebook2.services.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception {
        userService.deleteAll();
        roleService.deleteAll();

        //Create roles
        Role r1 = new Role("admin");
        r1 = roleService.save(r1);

        Role r2 = new Role("user");
        r2 = roleService.save(r2);


        //Setup Users
        User u1 = new User("admin", "password", "admin@admin.com");
        u1.getRoles().add(new UserRoles(u1, r1));

        Recipe recipe = new Recipe("Recipe Name", "Main", u1);

        Ingredient ingredient1 = new Ingredient("Onion", 1, "Lb", recipe);
        Ingredient ingredient2 = new Ingredient("Tomato", 2, "Lb", recipe);
        Ingredient ingredient3 = new Ingredient("Carrot", 3, "Lb", recipe);
        Ingredient ingredient4 = new Ingredient("Ginger", 4, "Lb", recipe);
        Ingredient ingredient5 = new Ingredient("Egg", 5, "Lb", recipe);
        Step step1 = new Step(1, "Cook it lightly until golden brown", recipe);
        Step step2 = new Step(2, "Put it in the pot and then churn", recipe);
        Step step3 = new Step(3, "Lightly baste the mouth watering taste", recipe);
        Step step4 = new Step(4, "Eat it", recipe);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);
        recipe.getIngredients().add(ingredient3);
        recipe.getIngredients().add(ingredient4);
        recipe.getIngredients().add(ingredient5);

        recipe.getSteps().add(step1);
        recipe.getSteps().add(step2);
        recipe.getSteps().add(step3);
        recipe.getSteps().add(step4);

        u1.getRecipes().add(recipe);

        userService.save(u1);

    }
}

