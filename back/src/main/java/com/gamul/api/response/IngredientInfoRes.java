package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.db.entity.*;
import com.gamul.db.repository.BasketRepository;
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

    double quantity;

    int volatility;

    boolean allergy;

    boolean favorite;

    boolean basket;

    @JsonProperty("high_class_id")
    int highClassId;

    @JsonProperty("high_class_name")
    String highClassName;

    Long views;

    public IngredientInfoRes(Ingredient ingredient, Day day, boolean allergy, boolean ingredientSelected, boolean basket, HighClass highClass, int volatility){
        this.ingredientId = ingredient.getId();
        this.name = ingredient.getMidClass();
        this.price = day.getPrice();
        this.unit = day.getUnit();
        this.quantity = day.getQuantity();
        this.volatility = volatility;
        this.allergy = allergy;
        this.favorite = ingredientSelected;
        this.basket = basket;
        this.highClassId = ingredient.getHighClass();
        this.highClassName = highClass.getName();
        this.views = ingredient.getViews();
    }

}
