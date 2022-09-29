package com.gamul.db.repository;

import com.gamul.db.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 월별 가격 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface MonthRepository extends JpaRepository<Month, Long> {
}
