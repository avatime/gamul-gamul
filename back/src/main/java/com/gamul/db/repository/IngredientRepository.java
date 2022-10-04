package com.gamul.db.repository;

import com.gamul.db.entity.Ingredient;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<List<Ingredient>> findAllByHighClass(int highClass);
    Optional<Ingredient> findById(Long ingredientId);
    Optional<List<Ingredient>> findByMidClassLike(String keyword);

    Optional<Ingredient> findByMidClass(String midClass);

}
