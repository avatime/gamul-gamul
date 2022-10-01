package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.db.entity.Ingredient;
import com.gamul.db.entity.Notice;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("NoticeRes")
public class NoticeRes {

    @JsonProperty("ingredient_id")
    Long ingredientId;

    String title;

    String message;

    public NoticeRes(Notice notice, String info){
        Ingredient ingredient = notice.getIngredientPriceNotice().getIngredient();
        this.ingredientId = ingredient.getId();
        this.title = ingredient.getMidClass() + (notice.isType()?" 상한가":" 하한가") + " 알림";
        this.message = ingredient.getMidClass() + info + (notice.isType()?" 까지 올랐어요! 다음을 기약해요.": " 까지 떨어졌어요! 지금이 바로 기회!!");
    }
}
