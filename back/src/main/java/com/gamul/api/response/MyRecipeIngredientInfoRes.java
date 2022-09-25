package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("IngredientInfoRes")
public class MyRecipeIngredientInfoRes {
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

}
