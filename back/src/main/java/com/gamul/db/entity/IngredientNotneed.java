package com.gamul.db.entity;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
public class IngredientNotneed extends BaseEntity{
    @Column
    private String quantity;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Recipe recipe;

    @Column
    String ingredient;

}




