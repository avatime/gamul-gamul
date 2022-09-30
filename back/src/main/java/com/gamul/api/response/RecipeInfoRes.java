package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.db.entity.Recipe;
import com.gamul.db.entity.RecipeSelected;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RecipeInfoRes")
public class RecipeInfoRes {
    @JsonProperty("recipe_id")
    Long recipeId;

    @JsonProperty("image_path")
    String imagePath;

    String name;

    String desc;

    boolean bookmark;

    public RecipeInfoRes(Long recipeId, String imagePath, String description, String name, boolean bookmark){
        this.recipeId = recipeId;
        this.imagePath = imagePath;
        this.desc = description;
        this.name = name;
        this.bookmark = bookmark;
    }
}
