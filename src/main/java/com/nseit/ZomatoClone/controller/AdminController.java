package com.nseit.ZomatoClone.controller;

import com.nseit.ZomatoClone.model.Restaurant;
import com.nseit.ZomatoClone.response.APIResponse;
import com.nseit.ZomatoClone.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurant")
    public ResponseEntity<APIResponse> addCategory(@RequestBody Restaurant restaurant) {
        APIResponse apiResponse = new APIResponse();
        Restaurant cat = restaurantService.addRestaurant(restaurant);
        if (cat == null) {
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(cat);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
