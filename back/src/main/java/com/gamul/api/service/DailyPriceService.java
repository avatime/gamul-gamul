package com.gamul.api.service;

import com.gamul.db.entity.Day;

import java.util.List;

public interface DailyPriceService {
    List<Day> findDailyPrices(Long ingredientId, int type) throws Exception;
    Day findDailyPrice(Long ingredientId, int type) throws Exception;
}
