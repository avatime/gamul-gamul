package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.db.entity.RecipeOrder;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RecipeProcedureRes")
public class RecipeProcedureRes {
    @JsonProperty("image_path")
    String imagePath;
    String description;

    public RecipeProcedureRes(RecipeOrder recipeOrder){
        this.imagePath = recipeOrder.getImagePath();
        this.description = recipeOrder.getDescription();
    }
}
