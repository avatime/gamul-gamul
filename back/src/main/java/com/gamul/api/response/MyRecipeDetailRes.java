package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("MyRecipeDetailRes")
public class MyRecipeDetailRes {
    @JsonProperty("total_price")
    int totalPrice;

    @JsonProperty("ingredient_list")
    List<IngredientInfoRes> ingredientList;

    @JsonProperty("price_transition_info")
    PriceTransitionInfoRes priceTransitionInfo;

    @JsonProperty("image_path")
    String imagePath;

    String name;
}
