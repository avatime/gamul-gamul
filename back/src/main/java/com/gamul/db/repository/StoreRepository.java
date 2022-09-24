package com.gamul.db.repository;

import com.gamul.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long storeId);
//    List<Store> findByLatitudeLessThanEqualAndLatitudeGreaterThanEqualAndLongitudeLessThanEquealAndLongitudeGreaterThanEqual(double southWestLatitude, double northEastLatitude, double southWestLongitude, double northEastLongitude);

    @Query("SELECT store FROM Store store WHERE store.latitude >= :southWestLatitude and store.latitude <= :northEastLatitude and store.longitude >= :southWestLongitude and store.longitude <= :northEastLongitude")
    List<Store> findByLatitudeAndLongitude(double southWestLatitude, double northEastLatitude, double southWestLongitude, double northEastLongitude);
}
