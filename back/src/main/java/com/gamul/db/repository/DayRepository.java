package com.gamul.db.repository;

import com.gamul.db.entity.Day;
import com.gamul.db.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 일자별 가격 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface DayRepository extends JpaRepository<Day, Long> {
    @Query(value = "select p.datetime, p.price from Day p where p.type = :type and p.ingredient.id = :#{#paramIngredient.id}")
    public double getAvgPriceByIngredientAndType(@Param(value = "paramIngredient") Ingredient ingredient, @Param(value = "type") int type);
}
