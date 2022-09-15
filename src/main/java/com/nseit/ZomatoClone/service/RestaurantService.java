package com.nseit.ZomatoClone.service;

import com.nseit.ZomatoClone.exception.ResourceAlreadyExistException;
import com.nseit.ZomatoClone.exception.ResourceNotFoundException;
import com.nseit.ZomatoClone.model.Restaurant;
import com.nseit.ZomatoClone.repository.CartRepository;
import com.nseit.ZomatoClone.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    public RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restaurant) {
        boolean isExists = restaurantRepository.findByRestaurantName(restaurant.getRestaurantName()).isPresent();
        if (isExists)
            throw new ResourceAlreadyExistException("Restaurant already exists.");
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> viewAllRestaurant() {
        return restaurantRepository.findAll();
    }

    public void deleteRestaurant(Integer restaurantId) {
        restaurantRepository.findById(restaurantId).orElseThrow(()
                -> new ResourceNotFoundException("Invalid restaurant id :" + restaurantId));
        restaurantRepository.deleteById(restaurantId);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        if (restaurant.getRestaurantId() != null && restaurant.getRestaurantId() > 0) {
            restaurantRepository.findById(restaurant.getRestaurantId()).orElseThrow(() ->
                    new ResourceNotFoundException("No restaurant with id:" + restaurant.getRestaurantId()));

            return restaurantRepository.save(restaurant);
        } else {
            throw new ResourceNotFoundException("Invalid restaurantId");
        }

    }
}

