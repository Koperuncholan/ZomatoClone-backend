package com.nseit.ZomatoClone.repository;

import com.nseit.ZomatoClone.model.Cart;
import com.nseit.ZomatoClone.model.FoodUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByFoodUser(FoodUser foodUser);
}
