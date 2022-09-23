package com.gamul.db.repository;

import com.gamul.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long storeId);

}
