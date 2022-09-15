package com.nseit.ZomatoClone.service;

import com.nseit.ZomatoClone.exception.ResourceNotFoundException;
import com.nseit.ZomatoClone.model.Cart;
import com.nseit.ZomatoClone.model.CartDish;
import com.nseit.ZomatoClone.model.Dish;
import com.nseit.ZomatoClone.model.FoodUser;
import com.nseit.ZomatoClone.repository.CartDishRepository;
import com.nseit.ZomatoClone.repository.CartRepository;
import com.nseit.ZomatoClone.repository.DishRepository;
import com.nseit.ZomatoClone.repository.UserRepository;
import com.nseit.ZomatoClone.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    public CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CartDishRepository cartDishRepository;

    public List<CartDish> addToCart(CartRequest cartRequest) {
        List<CartDish> cartDishes = new ArrayList<>();

        FoodUser foodUser = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id "
                        + cartRequest.getUserId()));

        Dish dish = dishRepository.findById(cartRequest.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Dish does not exist with id "
                        + cartRequest.getBookId()));

        Cart cart = cartRepository.findByFoodUser(foodUser)
                .orElseThrow(() -> new ResourceNotFoundException("No dishes in the cart for the user"));

        if (cartDishRepository.findByDishAndCart(dish, cart).isPresent()) {
            cartDishes = cartDishRepository.findByDishAndCart(dish, cart).get();
            boolean isExist = false;
            for (CartDish cartDish : cartDishes) {
                if (cartDish.getDish().equals(dish)) {
                    cartDish.setCount(cartRequest.getCount());
                    cartDishRepository.save(cartDish);
                    isExist = true;
                }
            }
            if (!isExist) {
                cartDishes.add(addDishToCart(dish, cart, cartRequest.getCount()));
            }
        } else {
            cartDishes.add(addDishToCart(dish, cart, cartRequest.getCount()));
        }

        return cartDishRepository.findByCart(foodUser.getCart())
                .orElseThrow(() -> new ResourceNotFoundException("No dishes in the cart for the user"));
    }

    private CartDish addDishToCart(Dish dish, Cart cart, int count) {
        CartDish cartDish = new CartDish();
        cartDish.setDish(dish);
        cartDish.setCart(cart);
        cartDish.setCount(count);
        cartDishRepository.save(cartDish);
        return cartDish;
    }

    public List<CartDish> showCartOfUserById(Integer userId) {
        FoodUser foodUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id "
                        + userId));

        return cartDishRepository.findByCart(foodUser.getCart())
                .orElseThrow(() -> new ResourceNotFoundException("No dishes in the cart for the user"));
    }

    public List<CartDish> removeBookFromCart(Integer cartBookId) {
        CartDish cartDish = cartDishRepository.findById(cartBookId)
                .orElseThrow(() -> new ResourceNotFoundException("No dishes in the cart for the user"));

        cartDishRepository.deleteById(cartBookId);

        return cartDishRepository.findByCart(cartDish.getCart())
                .orElseThrow(() -> new ResourceNotFoundException("No dishes in the cart for the user"));

    }

}




