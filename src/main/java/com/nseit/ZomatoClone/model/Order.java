package com.nseit.ZomatoClone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer orderId;
    private Date date;
    private Time time;

    private Boolean isCancelled = false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_order",referencedColumnName = "userId")
    private FoodUser foodUser;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "ordered_dish",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "dish_id",referencedColumnName = "dishId"))
    private Set<Dish> dishes;


}
