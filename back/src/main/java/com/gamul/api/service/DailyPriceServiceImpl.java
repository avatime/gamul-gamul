package com.gamul.api.service;

import com.gamul.db.entity.Day;
import com.gamul.db.entity.Month;
import com.gamul.db.entity.Year;
import com.gamul.db.repository.DayRepository;
import com.gamul.db.repository.MonthRepository;
import com.gamul.db.repository.YearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DailyPriceServiceImpl")
@RequiredArgsConstructor
public class DailyPriceServiceImpl implements DailyPriceService {
    @Autowired
    DayRepository dayRepository;

    @Autowired
    MonthRepository monthRepository;
    @Autowired
    YearRepository yearRepository;

    @Override
    public List<Day> findDailyPrices(Long ingredientId, int type) throws Exception {
//        return dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredientId, type);
        Day day = dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDescUnit(ingredientId, type);
        return dayRepository.findTop10ByIngredientIdAndTypeAndUnitOrderByDatetimeDesc(ingredientId, type, day.getUnit());
    }

    @Override
    public Day findDailyPrice(Long ingredientId, int type) throws Exception {
        return dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDescUnit(ingredientId, type);
    }

    @Override
    public List<Year> findYearlyPrices(Long ingredientId, int type) throws Exception {
        return yearRepository.findTop10ByIngredientIdOrderByDatetimeDesc(ingredientId);
    }

    @Override
    public Year findYearlyPrice(Long ingredientId, int type) throws Exception {
        return yearRepository.findTop1ByIngredientIdOrderByDatetimeDesc(ingredientId);
    }

    @Override
    public List<Month> findMonthlyPrices(Long ingredientId, int type) throws Exception {
        Month month = monthRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDescUnit(ingredientId, type);
        return monthRepository.findTop10ByIngredientIdAndTypeAndUnitOrderByDatetimeDesc(ingredientId, type, month.getUnit());
    }

    @Override
    public Month findMonthlyPrice(Long ingredientId, int type) throws Exception {
        return monthRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDescUnit(ingredientId, type);
    }
}
