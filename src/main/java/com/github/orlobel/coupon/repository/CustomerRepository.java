package com.github.orlobel.coupon.repository;

import com.github.orlobel.coupon.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
