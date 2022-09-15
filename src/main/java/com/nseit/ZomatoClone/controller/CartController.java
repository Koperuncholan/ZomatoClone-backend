package com.nseit.ZomatoClone.controller;

import com.nseit.ZomatoClone.model.CartDish;
import com.nseit.ZomatoClone.request.CartRequest;
import com.nseit.ZomatoClone.response.APIResponse;
import com.nseit.ZomatoClone.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(value = {"http://localhost:3000"})

public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private APIResponse apiResponse;

    //    @Secured({Role.ROLE_USER})
    @PostMapping("/add")
    public ResponseEntity<APIResponse> addToCart(@RequestBody CartRequest cartRequest) {
        List<CartDish> cartDishes = cartService.addToCart(cartRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartDishes);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //    @Secured({Role.ROLE_USER, Role.ROLE_ADMIN})
    @GetMapping("/user/{userId}")
    public ResponseEntity<APIResponse> showCartOfUserById(@PathVariable Integer userId) {
        List<CartDish> cartDishes = cartService.showCartOfUserById(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartDishes);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //    @Secured({Role.ROLE_USER})
    @DeleteMapping("/{cartBookId}")
    public ResponseEntity<APIResponse> removeBookFromCart(@PathVariable Integer cartBookId) {
        List<CartDish> cartDishes = cartService.removeBookFromCart(cartBookId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartDishes);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}


