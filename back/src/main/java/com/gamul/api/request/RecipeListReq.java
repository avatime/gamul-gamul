package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RecipeListReq")
public class RecipeListReq {
    @JsonProperty("order_type")
    int orderType;

    int page;

    int size;

    @JsonProperty("user_name")
    String userName;
}
