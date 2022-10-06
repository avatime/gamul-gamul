package com.gamul.api.service;

import com.gamul.db.entity.Day;
import com.gamul.db.entity.Month;
import com.gamul.db.entity.Year;

import java.util.List;

public interface DailyPriceService {
    List<Day> findDailyPrices(Long ingredientId, int type) throws Exception;
    Day findDailyPrice(Long ingredientId, int type) throws Exception;

    List<Year> findYearlyPrices(Long ingredientId, int type) throws Exception;
    Year findYearlyPrice(Long ingredientId, int type) throws Exception;

    List<Month> findMonthlyPrices(Long ingredientId, int type) throws Exception;
    Month findMonthlyPrice(Long ingredientId, int type) throws Exception;
}
