package com.nseit.ZomatoClone.service;

import com.nseit.ZomatoClone.exception.ResourceAlreadyExistException;
import com.nseit.ZomatoClone.exception.ResourceNotFoundException;
import com.nseit.ZomatoClone.model.Cart;
import com.nseit.ZomatoClone.model.FoodUser;
import com.nseit.ZomatoClone.model.Role;
import com.nseit.ZomatoClone.repository.CartRepository;
import com.nseit.ZomatoClone.repository.RoleRepository;
import com.nseit.ZomatoClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CartRepository cartRepository;

    public FoodUser registerAsCustomer(FoodUser foodUser) {
        FoodUser user = userRepository.findByUserName(foodUser.getUserName());
        if (user != null) {
            throw new ResourceAlreadyExistException("User Already Exception");
        }
        Role role = roleRepository.findByName(Role.ROLE_USER);
        foodUser.setRoles(Set.of(role));
        foodUser.setPassword(bCryptPasswordEncoder.encode(foodUser.getPassword()));
        foodUser = userRepository.save(foodUser);
        cartRepository.save(new Cart(foodUser));
        return foodUser;

    }

    public FoodUser loginAsCustomer(FoodUser foodUser) {
        FoodUser user = userRepository.findByUserName(foodUser.getUserName());
        if (user != null && bCryptPasswordEncoder.matches(foodUser.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new ResourceNotFoundException("Invalid Username or Password");
        }
    }

    public List<FoodUser> getAll() {
        return userRepository.findAll();
    }
}
