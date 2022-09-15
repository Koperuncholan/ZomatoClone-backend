package com.nseit.ZomatoClone.repository;

import com.nseit.ZomatoClone.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    Optional<Restaurant> findByRestaurantName(String restaurantName);
}
