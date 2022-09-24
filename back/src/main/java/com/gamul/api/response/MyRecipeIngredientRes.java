package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("MyRecipeIngredient")
public class MyRecipeIngredientRes {
    @JsonProperty("ingredient_id")
    Long ingredientId;

    int quantity;
}
