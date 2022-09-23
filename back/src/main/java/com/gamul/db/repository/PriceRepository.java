package com.gamul.db.repository;

import com.gamul.db.entity.Ingredient;
import com.gamul.db.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findByIngredientId(Long ingredientId);

    List<Price> findByStoreIdAndDateTime(Long storeId, LocalDate datetime);

}
