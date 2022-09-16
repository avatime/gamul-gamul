package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("IngredientLimitPriceRes")
public class IngredientLimitPriceRes {
    @JsonProperty("ingredient_id")
    int ingredientId;

    @JsonProperty("upper_limit_price")
    int upperLimitPrice;

    @JsonProperty("lower_limit_price")
    int lowerLimitPrice;
}
