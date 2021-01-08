package com.local.kaiblt.recipebook2.controllers;

import com.local.kaiblt.recipebook2.models.Recipe;
import com.local.kaiblt.recipebook2.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

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

}

