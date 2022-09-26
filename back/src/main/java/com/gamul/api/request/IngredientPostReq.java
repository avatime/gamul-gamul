package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("IngredientPostReq")
public class IngredientPostReq {
    @JsonProperty("ingredient_id")
    Long ingredientId;

    public IngredientPostReq(Long id) {
        this.ingredientId = id;
    }
}
