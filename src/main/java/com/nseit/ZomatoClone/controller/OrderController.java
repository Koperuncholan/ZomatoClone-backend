package com.nseit.ZomatoClone.controller;

import com.nseit.ZomatoClone.model.Order;
import com.nseit.ZomatoClone.request.CancelOrderRequest;
import com.nseit.ZomatoClone.request.OrderAllRequest;
import com.nseit.ZomatoClone.request.OrderRequest;
import com.nseit.ZomatoClone.response.APIResponse;
import com.nseit.ZomatoClone.response.SuccessResponse;
import com.nseit.ZomatoClone.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:3000"})
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private APIResponse apiResponse;

    //    @Secured({Role.ROLE_USER})
    @PostMapping("/all")
    public ResponseEntity<APIResponse> orderDishFromCart(@RequestBody OrderAllRequest orderAllRequest) {
        SuccessResponse successResponse = orderService.orderBookFromCart(orderAllRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(successResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //    @Secured({Role.ROLE_USER})
    @PostMapping
    public ResponseEntity<APIResponse> orderDish(@RequestBody OrderRequest orderRequest) {
        SuccessResponse successResponse = orderService.orderBook(orderRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(successResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //    @Secured({Role.ROLE_ADMIN})
    @GetMapping("/all")
    public ResponseEntity<APIResponse> viewAllOrders() {
        List<Order> orders = orderService.showAllOrders();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orders);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //    @Secured({Role.ROLE_USER, Role.ROLE_ADMIN})
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> viewOrderHistory(@PathVariable Integer userId) {
        List<Order> orders = orderService.showOrderHistory(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orders);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //    @Secured({Role.ROLE_USER})
    @PostMapping("/cancelOrder")
    public ResponseEntity<APIResponse> cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest) {
        List<Order> orders = orderService.cancelOrder(cancelOrderRequest.getOrderId());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orders);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

