package com.gamul.db.repository;

import com.gamul.db.entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 연도별 가격 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface YearRepository extends JpaRepository<Year, Long> {
    public List<Year> findTop10ByIngredientIdOrderByDatetimeDesc(Long ingredientId);
    public Year findTop1ByIngredientIdOrderByDatetimeDesc(Long ingredientId);
}
