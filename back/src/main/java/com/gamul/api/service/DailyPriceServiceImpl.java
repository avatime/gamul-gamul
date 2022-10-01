package com.gamul.api.service;

import com.gamul.db.entity.Day;
import com.gamul.db.repository.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DailyPriceServiceImpl")
@RequiredArgsConstructor
public class DailyPriceServiceImpl implements DailyPriceService {
    @Autowired
    DayRepository dayRepository;

    @Override
    public List<Day> findDailyPrices(Long ingredientId, int type) throws Exception {
        return dayRepository.findTop10ByIngredientIdAndTypeOrderByDatetimeDesc(ingredientId, type);
    }

    @Override
    public Day findDailyPrice(Long ingredientId, int type) throws Exception {
        return dayRepository.findTop1ByIngredientIdAndTypeOrderByDatetimeDesc(ingredientId, type);
    }
}
