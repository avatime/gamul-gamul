package com.gamul.db.repository;

import com.gamul.db.entity.IngredientSelected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientSelectedRepository extends JpaRepository<IngredientSelected, Long> {
    List<IngredientSelected> findAllByUserId(Long userId);
    Optional<IngredientSelected> findByIngredientId(Long ingredientId);
    IngredientSelected findByUserIdAndIngredientId(Long userId, Long ingredientId);
    Boolean existsByUserIdAndIngredientId(Long userId, Long ingredientId);
}
