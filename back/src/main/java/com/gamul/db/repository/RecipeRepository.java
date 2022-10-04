package com.gamul.db.repository;

import com.gamul.db.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
//    @Query(value = "SELECT r.* FROM Recipe r WHERE FUNCTION('REGEXP', ^[가-힣], :regex = true) ")
//
//    @Query("SELECT recipe FROM RECIPE recipe ORDER BY (CASE WHEN ASCII(SUBSTRING(name, 1)) between 48 and 57 THEN 3 CASE WHEN ASCII(SUBSTRING(name,1))<123 then 2 else 1), name")
//    @Query("select r from Recipe r where trim(r.name)")
    Page<Recipe> findAll(Pageable pageable);
    Optional<Recipe> findById(Long recipeId);
    Optional<List<Recipe>> findByNameLike(String keyword);
    List<Recipe> findTop30ByNameLike(String keyword);

}
