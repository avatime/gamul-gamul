package com.gamul.db.repository;

import com.gamul.db.entity.Allergy;
import com.gamul.db.entity.Ingredient;
import com.gamul.db.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    Optional<Allergy> findByIngredientId(Long ingredientId);
}
