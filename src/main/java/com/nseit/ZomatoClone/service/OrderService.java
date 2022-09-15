package com.nseit.ZomatoClone.service;

import com.nseit.ZomatoClone.exception.ResourceNotFoundException;
import com.nseit.ZomatoClone.model.*;
import com.nseit.ZomatoClone.repository.*;
import com.nseit.ZomatoClone.request.OrderAllRequest;
import com.nseit.ZomatoClone.request.OrderRequest;
import com.nseit.ZomatoClone.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDishRepository cartDishRepository;

    public SuccessResponse orderBookFromCart(OrderAllRequest orderAllRequest) {
        Set<Dish> dishes = new HashSet<>();
        Order order = new Order();

        FoodUser foodUser = userRepository.findById(orderAllRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id "
                        + orderAllRequest.getUserId()));

        Cart cart = cartRepository.findByFoodUser(foodUser)
                .orElseThrow(() -> new ResourceNotFoundException("Cart unavailable for the user"));

        cart.getCartDishes().forEach(cartDish -> dishes.add(cartDish.getDish()));
        order.setDishes(dishes);
        order.setFoodUser(foodUser);
        orderRepository.save(order);

        List<CartDish> cartDishes = cartDishRepository.findByCart(cart)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));

        for (CartDish cartDish : cartDishes) {
            cartDishRepository.delete(cartDish);
        }

        return new SuccessResponse();
    }

    public SuccessResponse orderBook(OrderRequest orderRequest) {
        Order order = new Order();

        FoodUser foodUser = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id "
                        + orderRequest.getUserId()));

        Dish dish = dishRepository.findById(orderRequest.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id "
                        + orderRequest.getBookId()));

        order.setDishes(Set.of(dish));
        order.setFoodUser(foodUser);
        orderRepository.save(order);


        return new SuccessResponse();
    }

    public List<Order> showAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> showOrderHistory(Integer userId) {
        FoodUser foodUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id "
                        + userId));

        return orderRepository.findByFoodUser(foodUser)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    }

    public List<Order> cancelOrder(Integer orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order does not exist with id "
                        + orderId));

        order.setIsCancelled(true);
        orderRepository.save(order);

        return orderRepository.findByFoodUser(order.getFoodUser())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));

    }
}


