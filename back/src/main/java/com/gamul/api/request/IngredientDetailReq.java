package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("IngredientDetailReq")
public class IngredientDetailReq {
    @JsonProperty("ingredient_id")
    Long ingredientId;

    @JsonProperty("user_name")
    String userName;
}
