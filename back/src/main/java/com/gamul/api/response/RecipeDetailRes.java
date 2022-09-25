package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("RecipeDetailRes")
public class RecipeDetailRes {
    @JsonProperty("recipe_info")
    RecipeInfoRes recipeInfoRes;

    @JsonProperty("ingredient_list")
    List<IngredientInfoRes> ingredientList;

    @JsonProperty("extra_ingredient_list")
    List<String> extraIngredientList;

    @JsonProperty("youtube_list")
    List<YoutubeInfoRes> youtubeList;

    public RecipeDetailRes(RecipeInfoRes recipeInfoRes, List<IngredientInfoRes> ingredientList, List<String> extraIngredientList, List<YoutubeInfoRes> youtubeList){
        this.recipeInfoRes = recipeInfoRes;
        this.ingredientList = ingredientList;
        this.extraIngredientList = extraIngredientList;
        this.youtubeList = youtubeList;
    }
}
