package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@ApiModel("RecipeDetailRes")
public class RecipeDetailRes {
    @JsonProperty("recipe_info")
    RecipeInfoRes recipeInfo;

    @JsonProperty("ingredient_list")
    ArrayList<IngredientInfoRes> ingredientList;

    @JsonProperty("extra_ingredient_list")
    ArrayList<String> extraIngredientList;

    @JsonProperty("youtube_list")
    ArrayList<YoutubeInfoRes> youtubeList;
}
