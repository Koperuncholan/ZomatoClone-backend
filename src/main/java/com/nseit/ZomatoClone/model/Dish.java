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
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue
    private Integer dishId;
    private String dishName;
    private int dishPrice;
    private String image;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "fileId")
    private File file;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id",referencedColumnName = "restaurantId")
    private Restaurant restaurant;

    @JsonIgnore
    @ManyToMany(mappedBy = "dishes", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Cart> carts;

    @JsonIgnore
    @ManyToMany(mappedBy = "dishes", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CartDish> cartDishes;
}
