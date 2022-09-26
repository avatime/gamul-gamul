package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.api.request.IngredientPostReq;
import com.gamul.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("AllergyAlarmRes")
public class AllergyAlarmRes extends BaseResponseBody {

    @JsonProperty("ingredient_list")
    List<IngredientPostReq> ingredientList;

    public static AllergyAlarmRes of(Integer statusCode, String message, AllergyAlarmRes allergyAlarmRes) {
        AllergyAlarmRes res = new AllergyAlarmRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setIngredientList(allergyAlarmRes.getIngredientList());

        return res;
    }
}
