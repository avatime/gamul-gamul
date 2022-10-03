package com.gamul.db.repository;

import com.gamul.db.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 일자별 가격 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface DayRepository extends JpaRepository<Day, Long> {
    public List<Day> findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(Long ingredientId, int type);

    List<Day> findTop7ByIngredientIdAndTypeOrderByDatetimeDesc(Long ingredientId, int type);


    public Day findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(Long ingredientId, int type);

    public Day findTop1ByIngredientIdAndTypeOrderByDatetimeDescUnit(Long ingredientId, int type);
    public Optional<List<Day>> findTop10ByIngredientIdAndTypeAndUnitOrderByDatetimeDesc(Long ingredientId, int type, String unit);

    List<Day> findTop7ByIngredientIdAndTypeAndUnitOrderByDatetimeDesc(Long ingredientId, int type, String unit);
}
