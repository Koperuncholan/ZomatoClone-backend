package com.nseit.ZomatoClone.controller;

import com.nseit.ZomatoClone.exception.ResourceNotFoundException;
import com.nseit.ZomatoClone.exception.UnableToInsertException;
import com.nseit.ZomatoClone.exception.UnableToUpdateException;
import com.nseit.ZomatoClone.model.Restaurant;
import com.nseit.ZomatoClone.model.Role;
import com.nseit.ZomatoClone.response.APIResponse;
import com.nseit.ZomatoClone.response.SuccessResponse;
import com.nseit.ZomatoClone.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:3000"})
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private APIResponse apiResponse;

//    @Secured({Role.ROLE_ADMIN})
    @PostMapping
    public ResponseEntity<APIResponse> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant addedRestaurant = restaurantService.addRestaurant(restaurant);
        if (addedRestaurant == null) {
            throw new UnableToInsertException("Unable to insert Restaurant");
        }
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(addedRestaurant);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

//    @Secured({Role.ROLE_ADMIN})
    @PutMapping
    public ResponseEntity<APIResponse> updateRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant);
        if (updatedRestaurant == null) {
            throw new UnableToUpdateException("Unable to update Restaurant");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(updatedRestaurant);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
//
//    @Secured({Role.ROLE_ADMIN})
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<APIResponse> deleteRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(new SuccessResponse());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @Secured({Role.ROLE_ADMIN, Role.ROLE_USER})
    @GetMapping("/all")
    public ResponseEntity<APIResponse> viewAllRestaurant() {
        List<Restaurant> restaurants = restaurantService.viewAllRestaurant();
        if (restaurants == null) {
            throw new ResourceNotFoundException("Unable to view Restaurant");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(restaurants);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
