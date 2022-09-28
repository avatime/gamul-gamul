package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("IngredientLimitPriceRes")
public class IngredientLimitPriceRes extends BaseResponseBody {
    @JsonProperty("ingredient_id")
    Long ingredientId;

    @JsonProperty("upper_limit_price")
    int upperLimitPrice;

    @JsonProperty("lower_limit_price")
    int lowerLimitPrice;

    public static IngredientLimitPriceRes of(Integer statusCode, String message, IngredientLimitPriceRes ingredientLimitPriceRes){
        IngredientLimitPriceRes res = new IngredientLimitPriceRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setIngredientId(ingredientLimitPriceRes.getIngredientId());
        res.setUpperLimitPrice(ingredientLimitPriceRes.getUpperLimitPrice());
        res.setLowerLimitPrice(ingredientLimitPriceRes.getLowerLimitPrice());

        return res;
    }
}
