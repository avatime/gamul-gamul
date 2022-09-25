package com.gamul.api.service;

import com.gamul.api.request.IngredientLimitPricePostReq;
import com.gamul.api.response.AllergyAlarmRes;
import com.gamul.db.entity.Allergy;
import com.gamul.db.entity.IngredientPriceNotice;
import com.gamul.db.repository.AllergyRepository;
import com.gamul.db.repository.IngredientPriceNoticeRepository;
import com.gamul.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AlarmService")
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AllergyRepository allergyRepository;
    @Autowired
    IngredientPriceNoticeRepository ingredientPriceNoticeRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Allergy> getAllergyList(AllergyAlarmRes allergyAlarmRes) {
        return null;
    }

    @Override
    public List<IngredientPriceNotice> getNoticeList(IngredientLimitPricePostReq ingredientLimitPricePostReq) {
        return null;
    }

    @Override
    public void deleteMyAllergy(String userName) {
        allergyRepository.deleteAllByUserId(userRepository.findByUsername(userName).get().getId());
    }

    @Override
    public void deleteMyNotice(String userName) {
        ingredientPriceNoticeRepository.deleteAllByUserId(userRepository.findByUsername(userName).get().getId());
    }

    @Override
    public IngredientPriceNotice getNoticeDetail(String userName, Long ingredientId) {
        return ingredientPriceNoticeRepository.findByUserIdAndIngredientId(userRepository.findByUsername(userName).get().getId(), ingredientId).orElseGet(null);
    }
}