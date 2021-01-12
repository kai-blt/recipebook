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
        //Setup Recipe1
        Recipe recipe = new Recipe("Example Paella", "Main", "https://images.unsplash.com/photo-1512058564366-18510be2db19?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1352&q=80", newuser);

        Ingredient ingredient1 = new Ingredient("Onion", 1, "Diced", "Ing", recipe);
        Ingredient ingredient2 = new Ingredient("Bell Pepper", 1, "Diced", "Ing", recipe);
        Ingredient ingredient3 = new Ingredient("Garlic", 4, "Cloves", "Ing", recipe);
        Ingredient ingredient4 = new Ingredient("Tomatoes", 3, "Finely Diced", "Ing", recipe);
        Step step1 = new Step(1, "Add all ingredients", recipe);
        Step step2 = new Step(2, "Cook until golden", recipe);
        Step step3 = new Step(3, "Enjoy", recipe);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);
        recipe.getIngredients().add(ingredient3);
        recipe.getIngredients().add(ingredient4);

        recipe.getSteps().add(step1);
        recipe.getSteps().add(step2);
        recipe.getSteps().add(step3);

        newuser.getRecipes().add(recipe);


        //Setup Recipe2
        Recipe recipe2 = new Recipe("Ultimate Salad", "Main", "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80", newuser);

        Ingredient i1 = new Ingredient("Kale", 1, "Lb", "Ing", recipe2);
        Ingredient i2 = new Ingredient("Tomato", 1, "Lb", "Ing", recipe2);
        Step s1 = new Step(1, "Massage Kale", recipe2);
        Step s2 = new Step(2, "Enjoy", recipe2);

        recipe2.getIngredients().add(i1);
        recipe2.getIngredients().add(i2);
        recipe2.getSteps().add(s1);
        recipe2.getSteps().add(s2);

        newuser.getRecipes().add(recipe2);

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
