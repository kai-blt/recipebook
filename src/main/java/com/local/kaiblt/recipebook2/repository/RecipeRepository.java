package com.local.kaiblt.recipebook2.repository;

import com.local.kaiblt.recipebook2.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
