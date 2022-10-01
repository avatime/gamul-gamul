package com.gamul.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Day extends BaseEntity {
    @Column
    private String datetime;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ingredient ingredient;

    @Column
    private int price;

    @Column
    private int quantity;

    @Column
    private String unit;

    @Column
    private int type;
}