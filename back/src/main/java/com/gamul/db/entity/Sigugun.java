package com.gamul.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
public class Sigugun extends BaseEntity{
    @Column(nullable = false)
    private String sigugunName;

    @OneToOne(mappedBy = "sigugun")
    private Store store;
}
