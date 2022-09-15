package com.nseit.ZomatoClone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class FoodUser {
    @Id
    @GeneratedValue
    private Integer userId;
    private String userName;
    private String password;
    private Long phoneNumber;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "foodUser", cascade = CascadeType.ALL)
    private Set<Order> orders;

    @JsonIgnore
    @OneToOne(mappedBy = "foodUser", cascade = CascadeType.ALL)
    private Cart cart;

    @ManyToMany
    @JsonIgnore
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "roleId")})
    private Set<Role> roles;

    public FoodUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}

