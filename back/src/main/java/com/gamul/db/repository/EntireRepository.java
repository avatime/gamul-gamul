package com.gamul.db.repository;

import com.gamul.api.response.TestInfoRes;
import com.gamul.db.entity.Entire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntireRepository extends JpaRepository<Entire, Long> {

//    List<Entire> findAllByIngredientIdAndTypeAndDateTime(Long ingredientId, int type, String dateTime);
    @Query(value = "SELECT AVG(entire.price) as Price, entire.date_time as Datetime FROM entire where entire.ingredient_id = :ingredientId and entire.type = :type group by ingredient_id, type, date_time order by date_time desc limit 7", nativeQuery = true)
    List<Object[]> findAllByIngredientIdAndType(@Param("ingredientId") Long ingredientId, @Param("type") int type);
}
