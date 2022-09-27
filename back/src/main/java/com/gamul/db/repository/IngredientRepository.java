package com.gamul.db.repository;

import com.gamul.db.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<List<Ingredient>> findAllByHighClass(Long highClass);
    Optional<Ingredient> findById(Long ingredientId);

}
