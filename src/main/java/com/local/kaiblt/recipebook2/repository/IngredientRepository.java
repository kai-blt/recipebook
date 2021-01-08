package com.local.kaiblt.recipebook2.repository;

import com.local.kaiblt.recipebook2.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
