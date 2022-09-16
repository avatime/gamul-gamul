package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("IngredientListReq")
public class IngredientListReq {
    @JsonProperty("order_type")
    int orderType;

    @JsonProperty("high_class")
    int highClass;

}
