package com.example.Assignment.repo;

import com.example.Assignment.entity.user_entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    Customer  findByUsername(String username);

}
