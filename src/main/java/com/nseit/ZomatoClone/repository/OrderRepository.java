package com.nseit.ZomatoClone.repository;

import com.nseit.ZomatoClone.model.FoodUser;
import com.nseit.ZomatoClone.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<List<Order>> findByFoodUser(FoodUser foodUser);
}
