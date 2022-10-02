package com.gamul.db.repository;

import com.gamul.db.entity.Notice;
import com.gamul.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> getAllByIngredientPriceNotice_User(User user);
    List<Notice> getAllByCreatedTimeBetween(Date start, Date end);
}
