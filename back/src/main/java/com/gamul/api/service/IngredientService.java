package com.gamul.api.service;

import com.gamul.api.request.IngredientListReq;
import com.gamul.api.request.OfflineMartDetailInfoReq;
import com.gamul.api.request.OfflineMartInfoReq;
import com.gamul.api.response.IngredientDetailRes;
import com.gamul.api.response.IngredientInfoRes;
import com.gamul.api.response.OfflineMartInfoRes;

import com.gamul.db.entity.HighClass;
import lombok.RequiredArgsConstructor;

import java.util.List;

public interface IngredientService {
    List<IngredientInfoRes> getIngredientList(IngredientListReq ingredientListReq);
    List<IngredientInfoRes> getIngredientSelectedList(String userName);

    IngredientDetailRes getIngredientDetailInfo(Long ingredientId);

    List<HighClass> getHighClassList();

    void ingredientSelected(String userName, Long ingredientId);

    void ingredientBasket(String userName, Long ingredientId);

    List<OfflineMartInfoRes> getStoreList(OfflineMartInfoReq offlineMartInfoReq);

    List<IngredientInfoRes> getStoreIngredientList(OfflineMartDetailInfoReq offlineMartDetailInfoReq);

    List<IngredientInfoRes> getBasketList(String userName);

    String getOnlineIngredientInfo(Long ingredientId);
}
