package com.gamul.db.entity;

import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Month extends BaseEntity {
    @Column
    private String datetime;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ingredient ingredient;

    @Column
    private int price;

    @Column
    private int type;

    @Column
    private int quantity;

    @Column
    private String unit;
}