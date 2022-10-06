package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("IngredientPricePostReq")
public class IngredientPricePostReq {
    @JsonProperty("ingredient_id")
    Long ingredientId;

    @JsonProperty("upper_limit_price")
    int upperLimitPrice;

    @JsonProperty("lower_limit_price")
    int lowerLimitPrice;
}
