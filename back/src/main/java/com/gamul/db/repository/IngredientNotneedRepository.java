package com.gamul.db.repository;

import com.gamul.db.entity.IngredientNotneed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientNotneedRepository extends JpaRepository<IngredientNotneed, Long> {
    Optional<List<IngredientNotneed>> findAllByRecipeId(Long recipeId);
}
