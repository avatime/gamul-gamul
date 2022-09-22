package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
@ApiModel("IngredientAllergyRegisterPostReq")
public class IngredientAllergyRegisterPostReq {
    @JsonProperty("user_name")
    @ApiModelProperty(name="유저 ID", example="your_Id")
    String userName;

    @JsonProperty("ingredient_list")
    ArrayList<IngredientPostReq> ingredientList;
}