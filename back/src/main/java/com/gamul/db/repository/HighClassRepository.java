package com.gamul.db.repository;

import com.gamul.db.entity.HighClass;
import com.gamul.db.entity.IngredientSelected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HighClassRepository extends JpaRepository<HighClass, Integer> {
    Optional<HighClass> findById(int highClass);
}
