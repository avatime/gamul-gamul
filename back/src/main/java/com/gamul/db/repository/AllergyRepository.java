package com.gamul.db.repository;

import com.gamul.db.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    Optional<Allergy> findByIngredientIdAndUserId(Long ingredientId, Long userId);
    Boolean existsByUserIdAndIngredientId(Long userId, Long ingredientId);
    @Transactional
    void deleteAllByUserId(Long userId);
    Optional<List<Allergy>> findAllByUserId(Long userId);
}
