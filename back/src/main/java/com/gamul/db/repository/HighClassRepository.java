package com.gamul.db.repository;

import com.gamul.db.entity.HighClass;
import com.gamul.db.entity.IngredientSelected;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HighClassRepository extends JpaRepository<HighClass, Long> {
    HighClass findById(int highClassId);
}
