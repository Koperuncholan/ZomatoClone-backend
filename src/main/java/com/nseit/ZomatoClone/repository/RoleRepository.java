package com.nseit.ZomatoClone.repository;

import com.nseit.ZomatoClone.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(String roleUser);

}
