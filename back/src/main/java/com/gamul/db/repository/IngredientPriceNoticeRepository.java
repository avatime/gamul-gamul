package com.gamul.db.repository;

import com.gamul.db.entity.IngredientPriceNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IngredientPriceNoticeRepository extends JpaRepository<IngredientPriceNotice, Long> {
    @Transactional
    void deleteAllByUserId(Long userId);
    Optional<List<IngredientPriceNotice>> findAllByUserId(Long userId);
    Optional<IngredientPriceNotice> findByUserIdAndIngredientId(Long userId, Long ingredientId);

}
