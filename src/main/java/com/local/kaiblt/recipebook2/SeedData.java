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
//        /////////////////
//        //Setup Recipe1//
//        /////////////////
//
//        /// Recipe(Name, Type, imageURL, newuser)
//        Recipe recipe1 = new Recipe("Checkerboard Cookies", "Sweets", "https://kaiblt-recipebook.netlify.app/cookies.jpg", u1);
//
//
//        // Ingredient(Name, Qty,Measurement, Group , recipe)
//        Ingredient ingredient1 = new Ingredient("Butter", "100", "g", "", recipe1);
//        Ingredient ingredient2 = new Ingredient("Sugar", "65", "g", "", recipe1);
//        Ingredient ingredient3 = new Ingredient("Egg Yolk", "1", "", "", recipe1);
//        Ingredient ingredient4 = new Ingredient("Egg White", "1", "", "", recipe1);
//        Ingredient ingredient5 = new Ingredient("Flour", "190", "g", "", recipe1);
//        Ingredient ingredient6 = new Ingredient("Matcha or Cacao Powder", "10", "g", "", recipe1);
//        Ingredient ingredient7 = new Ingredient("Milk", "", "Splash", "Optional", recipe1);
//
//
//        // Step (step number, instruction, recipe)
//        Step step1 = new Step(1, "Soften butter in room temperature. Add sugar and cream the butter using a whisk.", recipe1);
//        Step step2 = new Step(2, "Add egg yolk and mix well.", recipe1);
//        Step step3 = new Step(3, "Sift in flour and mix until it forms a rough dough. If it is too crumbly, add a splash of milk.", recipe1);
//        Step step4 = new Step(4, "Split the dough in two. Sift in matcha / cacao powder in one of them and mix until evenly incorporated.", recipe1);
//        Step step5 = new Step(5, "Form both dough into rectangle blocks. Separately cover with plastic wrap and let them rest in the fridge for 30 mins.", recipe1);
//        Step step6 = new Step(6, "Once the doughs are solid, cut each block in quarter to make 8 long sticks (4 for each color).", recipe1);
//        Step step7 = new Step(7, "Make the checkerboard by alternating the colors. Brush egg white on the touching surfaces to help them stick. Repeat for the other set.", recipe1);
//        Step step8 = new Step(8, "Separately cover the two blocks and put in the fridge for another 30 mins.", recipe1);
//        Step step9 = new Step(9, "Once they become solid again, start preheating the oven at 355°F. Carefully slice the blocks into 1/2 inch thick pieces.", recipe1);
//        Step step10 = new Step(10, "Bake for 15 to 18 mins.", recipe1);
//
//        // Add the ingredients
//        recipe1.getIngredients().add(ingredient1);
//        recipe1.getIngredients().add(ingredient2);
//        recipe1.getIngredients().add(ingredient3);
//        recipe1.getIngredients().add(ingredient4);
//        recipe1.getIngredients().add(ingredient5);
//        recipe1.getIngredients().add(ingredient6);
//        recipe1.getIngredients().add(ingredient7);
//
//
//        // Add the steps
//        recipe1.getSteps().add(step1);
//        recipe1.getSteps().add(step2);
//        recipe1.getSteps().add(step3);
//        recipe1.getSteps().add(step4);
//        recipe1.getSteps().add(step5);
//        recipe1.getSteps().add(step6);
//        recipe1.getSteps().add(step7);
//        recipe1.getSteps().add(step8);
//        recipe1.getSteps().add(step9);
//        recipe1.getSteps().add(step10);
//
//
//        // Add recipe to user
//        u1.getRecipes().add(recipe1);
//
//
//        /////////////////
//        //Setup Recipe2//
//        /////////////////
//
//        /// Recipe(Name, Type, imageURL, newuser)
//        Recipe recipe2 = new Recipe("Chicken Tikka Masala", "Main", "https://kaiblt-recipebook.netlify.app/tikka.jpg", u1);
//
//
//        // Ingredient(Name, Qty,Measurement, Group , recipe)
//        Ingredient r2ingredient1 = new Ingredient("Yogurt or Buttermilk", "1", "cup", "Marinade", recipe2 );
//        Ingredient r2ingredient2 = new Ingredient("Lemon Juice", "1", "Tbs", "Marinade", recipe2);
//        Ingredient r2ingredient3 = new Ingredient("Garlic", "3", "cloves", "Marinade", recipe2);
//        Ingredient r2ingredient4 = new Ingredient("Ginger", "1", "Tbs", "Marinade", recipe2);
//        Ingredient r2ingredient5 = new Ingredient("Garam Masala", "2", "tsp", "Marinade", recipe2);
//        Ingredient r2ingredient6 = new Ingredient("Salt", "1/2", "tsp", "Marinade", recipe2);
//        Ingredient r2ingredient7 = new Ingredient("Chicken Thighs", "1", "lb", "Marinade", recipe2);
//        Ingredient r2ingredient8 = new Ingredient("Olive Oil", "2", "Tbs", "Sauce", recipe2);
//        Ingredient r2ingredient9 = new Ingredient("Butter", "2", "Tbs", "Sauce", recipe2);
//        Ingredient r2ingredient10 = new Ingredient("Onions (finely chopped)", "2", "", "Sauce", recipe2);
//        Ingredient r2ingredient11 = new Ingredient("Garlic (finely chopped)", "3", "Cloves", "Sauce", recipe2);
//        Ingredient r2ingredient12 = new Ingredient("Ginger (grated)", "1", "Tbs", "Sauce", recipe2);
//        Ingredient r2ingredient13 = new Ingredient("Coriander", "1", "tsp", "Sauce", recipe2);
//        Ingredient r2ingredient14 = new Ingredient("Cumin", "1", "tsp", "Sauce", recipe2);
//        Ingredient r2ingredient15 = new Ingredient("Garam Masala", "1", "tsp", "Sauce", recipe2);
//        Ingredient r2ingredient16 = new Ingredient("Red Chili Pepper or Cayenne", "1", "tsp", "Sauce", recipe2);
//        Ingredient r2ingredient17 = new Ingredient("Tomato Sauce", "2", "cups", "Sauce", recipe2);
//        Ingredient r2ingredient18 = new Ingredient("Milk", "1", "cup", "Sauce", recipe2);
//        Ingredient r2ingredient19 = new Ingredient("Salt", "", "pinch", "Sauce", recipe2);
//
//
//
//        // Step (step number, instruction, recipe)
//        Step r2step1 = new Step(1, "In a bowl, combine all ingredients and marinate for 1 hour.", recipe2);
//        Step r2step2 = new Step(2, "In a large non-stick pan, heat oil on medium-high. Add chicken, ensuring not to crowd the pan. Cook for 2 - 3 mins per side until browned. Set aside. (It does not have to be cooked thoroughly at this point.)", recipe2);
//        Step r2step3 = new Step(3, "Add onions and butter into the same pan. Cook until soft and translucent.", recipe2);
//        Step r2step4 = new Step(4, "Add ginger and garlic and sauté for 30 to 45 sec. Then add coriander, cumin and garam masala. Fry until fragrant (about 15 to 20 sec).", recipe2);
//        Step r2step5 = new Step(5, "Add tomato sauce, red chili and season with salt to taste. Simmer for 15 to 20 mins on low, stirring occasionally, until tomato sauce thickens and takes on a deep reddish brown color.", recipe2);
//        Step r2step6 = new Step(6, "Add chicken and milk, and cook for another 10 mins until mixture is bubbling, glossy and thick.", recipe2);
//        Step r2step7 = new Step(7, "Serve with basmati rice, naan or pita (see recipe).", recipe2);
//
//        // Add the ingredients
//        recipe2.getIngredients().add(r2ingredient1);
//        recipe2.getIngredients().add(r2ingredient2);
//        recipe2.getIngredients().add(r2ingredient3);
//        recipe2.getIngredients().add(r2ingredient4);
//        recipe2.getIngredients().add(r2ingredient5);
//        recipe2.getIngredients().add(r2ingredient6);
//        recipe2.getIngredients().add(r2ingredient7);
//        recipe2.getIngredients().add(r2ingredient8);
//        recipe2.getIngredients().add(r2ingredient9);
//        recipe2.getIngredients().add(r2ingredient10);
//        recipe2.getIngredients().add(r2ingredient11);
//        recipe2.getIngredients().add(r2ingredient12);
//        recipe2.getIngredients().add(r2ingredient13);
//        recipe2.getIngredients().add(r2ingredient14);
//        recipe2.getIngredients().add(r2ingredient15);
//        recipe2.getIngredients().add(r2ingredient16);
//        recipe2.getIngredients().add(r2ingredient17);
//        recipe2.getIngredients().add(r2ingredient18);
//        recipe2.getIngredients().add(r2ingredient19);
//
//        // Add the steps
//        recipe2.getSteps().add(r2step1);
//        recipe2.getSteps().add(r2step2);
//        recipe2.getSteps().add(r2step3);
//        recipe2.getSteps().add(r2step4);
//        recipe2.getSteps().add(r2step5);
//        recipe2.getSteps().add(r2step6);
//        recipe2.getSteps().add(r2step7);
//
//
//        // Add recipe to user
//        u1.getRecipes().add(recipe2);
//
//
//        userService.save(u1);
//    }
//}
//
