package com.gamul.api.service;

import com.gamul.db.entity.Allergy;
import com.gamul.db.entity.IngredientPriceNotice;
import com.gamul.db.entity.Notice;
import com.gamul.db.entity.User;

import java.util.List;

public interface AlarmService {
    List<Allergy> getAllergyList(User user);
    List<IngredientPriceNotice> getNoticeList(User user);
    void deleteMyAllergy(String userName);
    void deleteMyNotice(String userName);
    IngredientPriceNotice getNoticeDetail(User user, Long ingredientId);
    List<Allergy> saveAllAlergy(List<Allergy> list);
    List<IngredientPriceNotice> saveAllIngredientPriceNotice(List<IngredientPriceNotice> list);
    List<Notice> getAllNoticeByUser(User user);
}
