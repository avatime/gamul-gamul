package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MyRecipeIngredient")
public class MyRecipeIngredient {
    @JsonProperty("ingredient_id")
    int ingredientId;

    int quantity;
}
