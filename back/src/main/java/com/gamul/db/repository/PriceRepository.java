package com.gamul.db.repository;

import com.gamul.db.entity.Ingredient;
import com.gamul.db.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findByIngredientId(Long ingredientId);
    List<Price> findByStoreIdAndDateTime(Long storeId, LocalDate datetime);

    Optional<List<Price>> findByIngredientIdAndStoreId(Long ingredientId, Long StoreId);

    public Price findTop1ByIngredientIdAndStoreIdOrderByDateTimeDesc(Long ingredientId, Long StoreId);

    @Query(value = "select AVG(p.price) from Price p where p.dateTime = :date and p.ingredient.id = :#{#paramIngredient.id}")
    public double getAvgPriceByDateAndIngredient(@Param(value = "date") String date, @Param(value = "paramIngredient")Ingredient ingredient);
}
