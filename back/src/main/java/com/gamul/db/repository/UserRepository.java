package com.gamul.db.repository;

import com.gamul.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
