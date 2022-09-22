package com.gamul.db.repository;

import com.gamul.db.entity.MyRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 마이레시피 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface MyRecipeRepository extends JpaRepository<MyRecipe, Long> {
}
