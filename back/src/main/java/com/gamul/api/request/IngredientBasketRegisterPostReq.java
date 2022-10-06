package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("IngredientBasketRegisterPostReq")
public class IngredientBasketRegisterPostReq {
    @JsonProperty("user_name")
    @ApiModelProperty(name="유저 ID", example="your_Id")
    String userName;

    @JsonProperty("ingredient_id")
    int ingredientId;
}
