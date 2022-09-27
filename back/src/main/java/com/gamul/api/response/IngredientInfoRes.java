package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.db.entity.*;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("IngredientInfoRes")
public class IngredientInfoRes {
    @JsonProperty("ingredient_id")
    Long ingredientId;

    String name;

    int price;

    String unit;

    int quantity;

    int volatility;

    boolean allergy;

    boolean favorite;

    boolean basket;

    @JsonProperty("high_class_id")
    Long highClassId;

    @JsonProperty("high_class_name")
    String highClassName;

    Long views;

    public IngredientInfoRes(Ingredient ingredient, Price price, HighClass highClass){
        this.ingredientId = ingredient.getId();
        this.name = ingredient.getMidClass();
        this.unit = price.getUnit();
        this.quantity = price.getQuantity();
        this.allergy = false;
        this.favorite = false;
        this.basket = false;
        this.highClassId = ingredient.getHighClass();
        this.highClassName = highClass.getName();
        this.views = ingredient.getViews();
    }

}
