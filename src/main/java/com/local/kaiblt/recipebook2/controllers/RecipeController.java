package com.local.kaiblt.recipebook2.controllers;

import com.local.kaiblt.recipebook2.models.Recipe;
import com.local.kaiblt.recipebook2.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // POST http://localhost:2019/recipes/recipe/
    // Add a recipe to the database
    @PostMapping(value = "/recipe", consumes = "application/json")
    public ResponseEntity<?> addRecipe(
        @Valid
        @RequestBody
        Recipe newRecipe) throws URISyntaxException {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

        newRecipe = recipeService.save(newRecipe);

        HttpHeaders responseHeaders = new HttpHeaders();

        URI newRecipeURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/recipeid}")
                .buildAndExpand(newRecipe.getRecipeid())
                .toUri();
        responseHeaders.setLocation(newRecipeURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }


    // PUT http://localhost:2019/recipes/recipe/{recipeid}
    // Update specific recipe using the provided recipeid
    @PutMapping(value = "/recipe/{recipeid}", consumes = "application/json")
    public ResponseEntity<?> updateRecipe(
        @PathVariable long recipeid,
        @Valid
        @RequestBody
        Recipe newRecipe) {

        recipeService.update(newRecipe, recipeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // DELETE http://localhost:2019/recipes/recipe/{recipeid}
    // Delete a specific recipe using the provided recipeid
    @DeleteMapping(value = "/recipe/{recipeid}")
    public ResponseEntity<?> deleteRecipe(@PathVariable long recipeid) {

        recipeService.delete(recipeid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

