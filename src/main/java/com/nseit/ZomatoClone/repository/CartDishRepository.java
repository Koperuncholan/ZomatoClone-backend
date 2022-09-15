package com.nseit.ZomatoClone.repository;

import com.nseit.ZomatoClone.model.Cart;
import com.nseit.ZomatoClone.model.CartDish;
import com.nseit.ZomatoClone.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDishRepository extends JpaRepository<CartDish,Integer> {
    Optional<List<CartDish>> findByDishAndCart(Dish dish, Cart cart);

    Optional<List<CartDish>> findByCart(Cart cart);
}
