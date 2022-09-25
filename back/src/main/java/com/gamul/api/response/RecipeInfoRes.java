package com.gamul.api.response;

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

    public RecipeInfoRes(Recipe recipe, RecipeSelected recipeSelected){
        this.recipeId = recipe.getId();
        this.imagePath = recipe.getThumbnail();
        this.desc = recipe.getInformation();
        this.name = recipe.getName();
        this.bookmark = recipeSelected.isActiveFlag();
    }
}
