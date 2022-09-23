package com.gamul.db.repository;

import com.gamul.db.entity.Basket;
import com.gamul.db.entity.IngredientSelected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByIngredientId(Long ingredientId);
    List<Basket> findAllByUserId(Long userId);
    Basket findByUserIdAndIngredientId(Long userId, Long ingredientId);
}
