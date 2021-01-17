//package com.local.kaiblt.recipebook2;
//
//import com.local.kaiblt.recipebook2.models.*;
//import com.local.kaiblt.recipebook2.services.RoleService;
//import com.local.kaiblt.recipebook2.services.UserService;
//import io.swagger.models.auth.In;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//
//@Transactional
//@Component
//public class SeedData implements CommandLineRunner {
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    RoleService roleService;
//
//    @Transactional
//    @Override
//    public void run(String[] args) throws Exception {
//        userService.deleteAll();
//        roleService.deleteAll();
//
//        //Create roles
//        Role r1 = new Role("admin");
//        r1 = roleService.save(r1);
//
//        Role r2 = new Role("user");
//        r2 = roleService.save(r2);
//
//
//        //Setup Admin
//        User u1 = new User("admin", "password", "admin@admin.com");
//        u1.getRoles().add(new UserRoles(u1, r1));
//
//        //Setup Recipe1
//        Recipe recipe = new Recipe("Example Paella", "Main", "https://images.unsplash.com/photo-1512058564366-18510be2db19?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1352&q=80", u1);
//
//        Ingredient ingredient1 = new Ingredient("Onion", "1", "Diced", "Ing", recipe);
//        Ingredient ingredient2 = new Ingredient("Bell Pepper", "1", "Diced", "Ing", recipe);
//        Ingredient ingredient3 = new Ingredient("Garlic", "4", "Cloves", "Ing", recipe);
//        Ingredient ingredient4 = new Ingredient("Tomatoes", "3", "Finely Diced", "Ing", recipe);
//        Step step1 = new Step(1, "Add all ingredients", recipe);
//        Step step2 = new Step(2, "Cook until golden", recipe);
//        Step step4 = new Step(4, "Enjoy", recipe);
//
//        recipe.getIngredients().add(ingredient1);
//        recipe.getIngredients().add(ingredient2);
//        recipe.getIngredients().add(ingredient3);
//        recipe.getIngredients().add(ingredient4);
//
//        recipe.getSteps().add(step1);
//        recipe.getSteps().add(step2);
//        recipe.getSteps().add(step4);
//
//        u1.getRecipes().add(recipe);
//
//        //Setup Recipe2
//        Recipe recipe2 = new Recipe("Ultimate Salad", "Main", "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80", u1);
//
//        Ingredient i1 = new Ingredient("Kale", "1", "Lb", "Ing", recipe2);
//        Ingredient i2 = new Ingredient("Tomato", "1", "Lb", "Ing", recipe2);
//        Step s1 = new Step(1, "Massage Kale", recipe2);
//        Step s2 = new Step(2, "Enjoy", recipe2);
//
//        recipe2.getIngredients().add(i1);
//        recipe2.getIngredients().add(i2);
//
//        recipe2.getSteps().add(s1);
//        recipe2.getSteps().add(s2);
//
//        u1.getRecipes().add(recipe2);
//
//        userService.save(u1);
//    }
//}
//
