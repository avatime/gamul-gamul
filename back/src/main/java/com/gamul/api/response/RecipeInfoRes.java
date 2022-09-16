package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RecipeInfoRes")
public class RecipeInfoRes {
    @JsonProperty("recipe_id")
    int recipeId;

    @JsonProperty("image_path")
    String image_path;

    String name;

    String desc;

    boolean bookmark;
}
