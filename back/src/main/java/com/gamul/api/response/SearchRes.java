package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("SearchRes")
public class SearchRes {
    @JsonProperty("ingredient_list")
    List<IngredientInfoRes> ingredientList;

    @JsonProperty("recipe_list")
    List<RecipeInfoRes> recipeList;
}
