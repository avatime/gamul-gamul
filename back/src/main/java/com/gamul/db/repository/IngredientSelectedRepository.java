package com.gamul.db.repository;

import com.gamul.db.entity.IngredientSelected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientSelectedRepository extends JpaRepository<IngredientSelected, Long> {
    List<IngredientSelected> findAllByUserId(Long userId);
    IngredientSelected findByIngredientId(Long ingredientId);
    IngredientSelected findByUserIdAndIngredientId(Long userId, Long ingredientId);
    Boolean existsByUserIdAndIngredientId(Long userId, Long ingredientId);
}
