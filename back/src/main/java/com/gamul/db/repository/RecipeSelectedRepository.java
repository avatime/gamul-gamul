package com.gamul.db.repository;

import com.gamul.db.entity.RecipeSelected;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeSelectedRepository extends JpaRepository<RecipeSelected, Long> {
    Optional<List<RecipeSelected>> findByUserId(Long userId);
    Optional<RecipeSelected> findByRecipeId(Long recipeId);

    Optional<RecipeSelected> findByUserIdAndRecipeId(Long userId, Long recipeId);
}
