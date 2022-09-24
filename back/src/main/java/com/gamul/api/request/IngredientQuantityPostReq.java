package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("IngredientQuantityPostReq")
public class IngredientQuantityPostReq {
    @JsonProperty("ingredient_id")
    Long ingredientId;

    int quantity;
}
