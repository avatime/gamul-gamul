package com.gamul.db.entity;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
public class HighClass extends BaseEntity{
    @Column(nullable = false, updatable = false)
    private String name;
}
