package com.nseit.ZomatoClone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue
    private Integer cartId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private FoodUser foodUser;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "cartId"),
            inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "dishId"))
    private Set<Dish> dishes;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartDish> cartDishes;

    public Cart(FoodUser foodUser) {
        this.foodUser = foodUser;
    }
}
