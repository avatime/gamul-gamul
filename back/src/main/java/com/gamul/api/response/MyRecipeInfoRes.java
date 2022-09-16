package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MyRecipeInfoRes")
public class MyRecipeInfoRes {
    @JsonProperty("my_recipe_id")
    int myRecipeId;

    @JsonProperty("image_path")
    String imagePath;

    String name;
}
