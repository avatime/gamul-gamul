package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@ApiModel("IngredientLimitPriceAlarmRes")
public class IngredientLimitPriceAlarmRes extends BaseResponseBody {

    @JsonProperty("ingredient_list")
    List<IngredientLimitPriceRes> ingredientList;

    public static IngredientLimitPriceAlarmRes of(Integer statusCode, String message, IngredientLimitPriceAlarmRes ingredientLimitPriceAlarmRes){
        IngredientLimitPriceAlarmRes res = new IngredientLimitPriceAlarmRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setIngredientList(ingredientLimitPriceAlarmRes.getIngredientList());

        return res;
    }
}
