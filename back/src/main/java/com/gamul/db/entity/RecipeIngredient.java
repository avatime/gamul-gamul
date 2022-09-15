package com.gamul.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
public class RecipeIngredient extends BaseEntity{

    @Column
    private String quantity;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private Ingredient ingredient;
}
