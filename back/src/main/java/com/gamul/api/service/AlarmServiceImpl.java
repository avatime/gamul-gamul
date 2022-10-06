package com.gamul.api.service;

import com.gamul.db.entity.Allergy;
import com.gamul.db.entity.IngredientPriceNotice;
import com.gamul.db.entity.Notice;
import com.gamul.db.entity.User;
import com.gamul.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public List<Allergy> getAllergyList(User user) {
        return allergyRepository.findAllByUserId(user.getId()).orElse(new ArrayList<>());
    }

    @Override
    public List<IngredientPriceNotice> getNoticeList(User user) {
        return ingredientPriceNoticeRepository.findAllByUserId(user.getId()).orElse(new ArrayList<>());
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
    public IngredientPriceNotice getNoticeDetail(User user, Long ingredientId) {
        return ingredientPriceNoticeRepository.findByUserIdAndIngredientId(user.getId(), ingredientId).orElse(null);
    }

    @Override
    public List<Allergy> saveAllAlergy(List<Allergy> list) {
        return allergyRepository.saveAll(list);
    }

    @Override
    public List<IngredientPriceNotice> saveAllIngredientPriceNotice(List<IngredientPriceNotice> list) {
        return ingredientPriceNoticeRepository.saveAll(list);
    }

    @Override
    public List<Notice> getAllNoticeByUser(User user) {
        return noticeRepository.getAllByIngredientPriceNotice_User(user);
    }

    @Override
    public List<Notice> getAllNotice() {
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
        return noticeRepository.getAllByCreatedTimeBetween(Timestamp.valueOf(start), Timestamp.valueOf(end));
    }
}