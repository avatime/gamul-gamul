package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("IngredientDetailRes")
public class IngredientDetailRes {
    @JsonProperty("ingredient_info")
    IngredientInfoRes ingredientInfo;

    Long views;

    @JsonProperty("price_transition_info")
    PriceTransitionInfoRes priceTransitionInfo;

    @JsonProperty("online_mart_info")
    List<OnlineMartInfoRes> onlineMartInfo;
}
