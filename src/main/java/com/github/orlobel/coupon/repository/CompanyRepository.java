package com.github.orlobel.coupon.repository;

import com.github.orlobel.coupon.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Boolean existsByNameAndEmail(String name, String email);

    Company findByNameAndEmail(String name, String email);

    Company findByEmailAndPassword(String email, String password);
}
