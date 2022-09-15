package com.nseit.ZomatoClone.service;

import com.nseit.ZomatoClone.exception.ResourceAlreadyExistException;
import com.nseit.ZomatoClone.exception.ResourceNotFoundException;
import com.nseit.ZomatoClone.model.Dish;
import com.nseit.ZomatoClone.model.Restaurant;
import com.nseit.ZomatoClone.repository.CartRepository;
import com.nseit.ZomatoClone.repository.DishRepository;
import com.nseit.ZomatoClone.repository.RestaurantRepository;
import com.nseit.ZomatoClone.request.DishRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired
    public DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Dish addDish(DishRequest dishRequest) {
        Dish dish = new Dish();

        Restaurant restaurant = restaurantRepository.findById(dishRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with id "
                        + dishRequest.getRestaurantId()));
        dish.setRestaurant(restaurant);
        dish.setDishName((dishRequest.getDishName()));
        dish.setDishPrice(dishRequest.getDishPrice());

        boolean isBookExists = dishRepository.findByDishName(dishRequest.getDishName()).isPresent();
        if (isBookExists)
            throw new ResourceAlreadyExistException("dish already exists.");

        return dishRepository.save(dish);
    }

    public Dish updateDish(DishRequest dishRequest) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishRequest, dish);
        if (dishRequest.getId() == null)
            throw new ResourceNotFoundException("No dish with id "
                    + dishRequest.getId());
        Restaurant restaurant = restaurantRepository.findById(dishRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("No restaurant with restaurant id "
                        + dishRequest.getRestaurantId()));
        dish.setRestaurant(restaurant);
        return dishRepository.save(dish);
    }

    public void deleteDish(Integer dishId) {
        dishRepository.findById(dishId).orElseThrow(() ->
                new ResourceNotFoundException("No dish with id "
                        + dishId));
        dishRepository.deleteById(dishId);
    }

    public List<Dish> viewAllDish() {
        return dishRepository.findAll();
    }

    public Dish findDishById(Integer dishId) {
        return dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find dish with id " + dishId));
    }
}


