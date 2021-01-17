package com.local.kaiblt.recipebook2.controllers;

import com.local.kaiblt.recipebook2.models.*;
import com.local.kaiblt.recipebook2.services.RecipeService;
import com.local.kaiblt.recipebook2.services.RoleService;
import com.local.kaiblt.recipebook2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class allows access to endpoints that are open to all users regardless of authentication status.
 * Its most important function is to allow a person to create their own username
 */
@RestController
public class OpenController
{
    /**
     * A method in this controller adds a new user to the application so needs access to User Services to do this.
     */
    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    /**
     * A method in this controller adds a new user to the application with the role User so needs access to Role Services to do this.
     */
    @Autowired
    private RoleService roleService;

    /**
     * This endpoint always anyone to create an account with the default role of USER. That role is hardcoded in this method.
     *
     * @param httpServletRequest the request that comes in for creating the new user
     * @param newminuser         A special minimum set of data that is needed to create a new user
     * @return The token access and other relevent data to token access. Status of CREATED. The location header to look up the new user.
     * @throws URISyntaxException we create some URIs during this method. If anything goes wrong with that creation, an exception is thrown.
     */
    @PostMapping(value = "/createnewuser",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addSelf(
            HttpServletRequest httpServletRequest,
            @Valid
            @RequestBody
                    UserMinimum newminuser)
            throws
            URISyntaxException
    {
        // Create the user
        User newuser = new User();

        newuser.setUsername(newminuser.getUsername());
        newuser.setPassword(newminuser.getPassword());
        newuser.setEmail(newminuser.getEmail());

        // add the default role of user
        Set<UserRoles> newRoles = new HashSet<>();

        //Check on path variable and add appropriate user account role
        newRoles.add(new UserRoles(newuser, roleService.findByName("user")));

        newuser.setRoles(newRoles);

        List<Recipe> recipeList = new ArrayList<>();

        /////////////////
        //Setup Recipe1//
        /////////////////

        /// Recipe(Name, Type, imageURL, newuser)
        Recipe recipe1 = new Recipe("Checkerboard Cookies", "Sweets", "https://kaiblt-recipebook.netlify.app/cookies.jpg", newuser);


        // Ingredient(Name, Qty,Measurement, Group , recipe)
        Ingredient ingredient1 = new Ingredient("Butter", "100", "g", "", recipe1);
        Ingredient ingredient2 = new Ingredient("Sugar", "65", "g", "", recipe1);
        Ingredient ingredient3 = new Ingredient("Egg Yolk", "1", "", "", recipe1);
        Ingredient ingredient4 = new Ingredient("Egg White", "1", "", "", recipe1);
        Ingredient ingredient5 = new Ingredient("Flour", "190", "g", "", recipe1);
        Ingredient ingredient6 = new Ingredient("Matcha or Cacao Powder", "10", "g", "", recipe1);
        Ingredient ingredient7 = new Ingredient("Milk", "", "Splash", "Optional", recipe1);


        // Step (step number, instruction, recipe)
        Step step1 = new Step(1, "Soften butter in room temperature. Add sugar and cream the butter using a whisk.", recipe1);
        Step step2 = new Step(2, "Add egg yolk and mix well.", recipe1);
        Step step3 = new Step(3, "Sift in flour and mix until it forms a rough dough. If it is too crumbly, add a splash of milk.", recipe1);
        Step step4 = new Step(4, "Split the dough in two. Sift in matcha / cacao powder in one of them and mix until evenly incorporated.", recipe1);
        Step step5 = new Step(5, "Form both dough into rectangle blocks. Separately cover with plastic wrap and let them rest in the fridge for 30 mins.", recipe1);
        Step step6 = new Step(6, "Once the doughs are solid, cut each block in quarter to make 8 long sticks (4 for each color).", recipe1);
        Step step7 = new Step(7, "Make the checkerboard by alternating the colors. Brush egg white on the touching surfaces to help them stick. Repeat for the other set.", recipe1);
        Step step8 = new Step(8, "Separately cover the two blocks and put in the fridge for another 30 mins.", recipe1);
        Step step9 = new Step(9, "Once they become solid again, start preheating the oven at 355°F. Carefully slice the blocks into 1/2 inch thick pieces.", recipe1);
        Step step10 = new Step(10, "Bake for 15 to 18 mins.", recipe1);

        // Add the ingredients
        recipe1.getIngredients().add(ingredient1);
        recipe1.getIngredients().add(ingredient2);
        recipe1.getIngredients().add(ingredient3);
        recipe1.getIngredients().add(ingredient4);
        recipe1.getIngredients().add(ingredient5);
        recipe1.getIngredients().add(ingredient6);
        recipe1.getIngredients().add(ingredient7);


        // Add the steps
        recipe1.getSteps().add(step1);
        recipe1.getSteps().add(step2);
        recipe1.getSteps().add(step3);
        recipe1.getSteps().add(step4);
        recipe1.getSteps().add(step5);
        recipe1.getSteps().add(step6);
        recipe1.getSteps().add(step7);
        recipe1.getSteps().add(step8);
        recipe1.getSteps().add(step9);
        recipe1.getSteps().add(step10);


        // Add recipe to user
        newuser.getRecipes().add(recipe1);


        /////////////////
        //Setup Recipe2//
        /////////////////

        /// Recipe(Name, Type, imageURL, newuser)
        Recipe recipe2 = new Recipe("Chicken Tikka Masala", "Main", "https://kaiblt-recipebook.netlify.app/tikka.jpg", newuser);


        // Ingredient(Name, Qty,Measurement, Group , recipe)
        Ingredient r2ingredient1 = new Ingredient("Yogurt or Buttermilk", "1", "cup", "Marinade", recipe2 );
        Ingredient r2ingredient2 = new Ingredient("Lemon Juice", "1", "Tbs", "Marinade", recipe2);
        Ingredient r2ingredient3 = new Ingredient("Garlic", "3", "cloves", "Marinade", recipe2);
        Ingredient r2ingredient4 = new Ingredient("Ginger", "1", "Tbs", "Marinade", recipe2);
        Ingredient r2ingredient5 = new Ingredient("Garam Masala", "2", "tsp", "Marinade", recipe2);
        Ingredient r2ingredient6 = new Ingredient("Salt", "1/2", "tsp", "Marinade", recipe2);
        Ingredient r2ingredient7 = new Ingredient("Chicken Thighs", "1", "lb", "Marinade", recipe2);
        Ingredient r2ingredient8 = new Ingredient("Olive Oil", "2", "Tbs", "Sauce", recipe2);
        Ingredient r2ingredient9 = new Ingredient("Butter", "2", "Tbs", "Sauce", recipe2);
        Ingredient r2ingredient10 = new Ingredient("Onions (finely chopped)", "2", "", "Sauce", recipe2);
        Ingredient r2ingredient11 = new Ingredient("Garlic (finely chopped)", "3", "Cloves", "Sauce", recipe2);
        Ingredient r2ingredient12 = new Ingredient("Ginger (grated)", "1", "Tbs", "Sauce", recipe2);
        Ingredient r2ingredient13 = new Ingredient("Coriander", "1", "tsp", "Sauce", recipe2);
        Ingredient r2ingredient14 = new Ingredient("Cumin", "1", "tsp", "Sauce", recipe2);
        Ingredient r2ingredient15 = new Ingredient("Garam Masala", "1", "tsp", "Sauce", recipe2);
        Ingredient r2ingredient16 = new Ingredient("Red Chili Pepper or Cayenne", "1", "tsp", "Sauce", recipe2);
        Ingredient r2ingredient17 = new Ingredient("Tomato Sauce", "2", "cups", "Sauce", recipe2);
        Ingredient r2ingredient18 = new Ingredient("Milk", "1", "cup", "Sauce", recipe2);
        Ingredient r2ingredient19 = new Ingredient("Salt", "", "pinch", "Sauce", recipe2);



        // Step (step number, instruction, recipe)
        Step r2step1 = new Step(1, "In a bowl, combine all ingredients and marinate for 1 hour.", recipe2);
        Step r2step2 = new Step(2, "In a large non-stick pan, heat oil on medium-high. Add chicken, ensuring not to crowd the pan. Cook for 2 - 3 mins per side until browned. Set aside. (It does not have to be cooked thoroughly at this point.)", recipe2);
        Step r2step3 = new Step(3, "Add onions and butter into the same pan. Cook until soft and translucent.", recipe2);
        Step r2step4 = new Step(4, "Add ginger and garlic and sauté for 30 to 45 sec. Then add coriander, cumin and garam masala. Fry until fragrant (about 15 to 20 sec).", recipe2);
        Step r2step5 = new Step(5, "Add tomato sauce, red chili and season with salt to taste. Simmer for 15 to 20 mins on low, stirring occasionally, until tomato sauce thickens and takes on a deep reddish brown color.", recipe2);
        Step r2step6 = new Step(6, "Add chicken and milk, and cook for another 10 mins until mixture is bubbling, glossy and thick.", recipe2);
        Step r2step7 = new Step(7, "Serve with basmati rice, naan or pita (see recipe).", recipe2);

        // Add the ingredients
        recipe2.getIngredients().add(r2ingredient1);
        recipe2.getIngredients().add(r2ingredient2);
        recipe2.getIngredients().add(r2ingredient3);
        recipe2.getIngredients().add(r2ingredient4);
        recipe2.getIngredients().add(r2ingredient5);
        recipe2.getIngredients().add(r2ingredient6);
        recipe2.getIngredients().add(r2ingredient7);
        recipe2.getIngredients().add(r2ingredient8);
        recipe2.getIngredients().add(r2ingredient9);
        recipe2.getIngredients().add(r2ingredient10);
        recipe2.getIngredients().add(r2ingredient11);
        recipe2.getIngredients().add(r2ingredient12);
        recipe2.getIngredients().add(r2ingredient13);
        recipe2.getIngredients().add(r2ingredient14);
        recipe2.getIngredients().add(r2ingredient15);
        recipe2.getIngredients().add(r2ingredient16);
        recipe2.getIngredients().add(r2ingredient17);
        recipe2.getIngredients().add(r2ingredient18);
        recipe2.getIngredients().add(r2ingredient19);

        // Add the steps
        recipe2.getSteps().add(r2step1);
        recipe2.getSteps().add(r2step2);
        recipe2.getSteps().add(r2step3);
        recipe2.getSteps().add(r2step4);
        recipe2.getSteps().add(r2step5);
        recipe2.getSteps().add(r2step6);
        recipe2.getSteps().add(r2step7);


        // Add recipe to user
        newuser.getRecipes().add(recipe2);


        // Save the user
        newuser = userService.save(newuser);


        // set the location header for the newly created resource
        // The location comes from a different controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/user/{userId}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        // return the access token
        // To get the access token, surf to the endpoint /login (which is always on the server where this is running)
        // just as if a client had done this.
        RestTemplate restTemplate = new RestTemplate();
        String requestURI = "http://localhost" + ":" + httpServletRequest.getLocalPort() + "/login";

        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(acceptableMediaTypes);
        headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
                System.getenv("OAUTHCLIENTSECRET"));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type",
                "password");
        map.add("scope",
                "read write trust");
        map.add("username",
                newminuser.getUsername());
        map.add("password",
                newminuser.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
                headers);

        String theToken = restTemplate.postForObject(requestURI,
                request,
                String.class);

        return new ResponseEntity<>(theToken,
                responseHeaders,
                HttpStatus.CREATED);
    }


    /**
     * Prevents no favicon.ico warning from appearing in the logs. @ApiIgnore tells Swagger to ignore documenting this as an endpoint.
     */
    @ApiIgnore
    @GetMapping("favicon.ico")
    public void returnNoFavicon()
    {

    }
}
