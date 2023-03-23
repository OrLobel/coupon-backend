package com.github.orlobel.coupon.service;

import com.github.orlobel.coupon.repository.CompanyRepository;
import com.github.orlobel.coupon.repository.CouponRepository;
import com.github.orlobel.coupon.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;
}
