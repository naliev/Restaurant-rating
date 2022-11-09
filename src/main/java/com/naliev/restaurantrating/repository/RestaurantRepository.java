package com.naliev.restaurantrating.repository;

import com.naliev.restaurantrating.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT DISTINCT r from Restaurant r JOIN FETCH r.menu")
    List<Restaurant> getAllWithMenu(LocalDate date);
}
