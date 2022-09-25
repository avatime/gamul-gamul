package com.gamul.db.repository;

import com.gamul.db.entity.RecipeImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeImageRepository extends JpaRepository<RecipeImage, Long> {
    Optional<List<RecipeImage>> findAllByRecipeId(Long recipeId);
}
