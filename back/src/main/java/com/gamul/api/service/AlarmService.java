package com.gamul.api.service;

import com.gamul.api.request.IngredientLimitPricePostReq;
import com.gamul.api.response.AllergyAlarmRes;
import com.gamul.db.entity.Allergy;
import com.gamul.db.entity.IngredientPriceNotice;

import java.util.List;

public interface AlarmService {
    List<Allergy> getAllergyList(AllergyAlarmRes allergyAlarmRes);
    List<IngredientPriceNotice> getNoticeList(IngredientLimitPricePostReq ingredientLimitPricePostReq);
    void deleteMyAllergy(String userName);
    void deleteMyNotice(String userName);
    IngredientPriceNotice getNoticeDetail(String userName, Long ingredientId);
}
