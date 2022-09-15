package com.nseit.ZomatoClone.repository;

import com.nseit.ZomatoClone.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish,Integer> {
    Optional<Object> findByDishName(String dishName);

}
