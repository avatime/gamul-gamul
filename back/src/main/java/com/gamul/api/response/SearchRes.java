package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@ApiModel("SearchRes")
public class SearchRes {
    @JsonProperty("ingredient_list")
    ArrayList<IngredientInfoRes> ingredientList;

    @JsonProperty("recipe_list")
    ArrayList<RecipeInfoRes> recipeList;
}
