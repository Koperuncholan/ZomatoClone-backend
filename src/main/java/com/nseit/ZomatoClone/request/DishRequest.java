package com.nseit.ZomatoClone.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequest {
    private Integer id;
    private Integer restaurantId;
    private String dishName;
    private String restaurantName;
    private Integer dishPrice;
}
