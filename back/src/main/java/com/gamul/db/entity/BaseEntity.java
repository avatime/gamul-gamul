package com.gamul.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/**
 * 모델 간 공통 사항 정의.
 */
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id = null;
}