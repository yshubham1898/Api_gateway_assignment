package com.example.Assignment.repo;

import com.example.Assignment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Customer,Long> {

    Customer findByUsername(String username);

}
