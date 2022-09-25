package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.api.request.IngredientPostReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("IngredientAllergyRegisterPostReq")
public class AllergyAlarmRes {
    @JsonProperty("user_name")
    @ApiModelProperty(name="유저 ID", example="your_Id")
    String userName;

    @JsonProperty("ingredient_list")
    List<IngredientPostReq> ingredientList;
}
