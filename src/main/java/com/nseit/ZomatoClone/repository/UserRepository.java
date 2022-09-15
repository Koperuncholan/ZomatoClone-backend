package com.nseit.ZomatoClone.repository;

import com.nseit.ZomatoClone.model.FoodUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<FoodUser,Integer> {

    FoodUser findByUserName(String userName);

}
