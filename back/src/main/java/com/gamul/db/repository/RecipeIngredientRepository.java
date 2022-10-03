package com.gamul.db.repository;

import com.gamul.db.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    Optional<List<RecipeIngredient>> findAllByIngredientId(Long ingredientId);
    Optional<List<RecipeIngredient>> findAllByRecipeId(Long recipeId);
    List<RecipeIngredient> findTop10ByIngredientIdOrderByRecipeViewsDesc(Long ingredientId);
}
