package com.github.orlobel.coupon.service;

import java.util.List;
import com.github.orlobel.coupon.beans.Coupon;
import com.github.orlobel.coupon.beans.Customer;
import com.github.orlobel.coupon.exception.CouponSystemErrors;

public interface CustomerService {
    Customer login(String email, String password);

    void purchaseCoupon(int customerId, int couponId) throws CouponSystemErrors;

    List<Coupon> getAllCoupons();

    List<Coupon> getCustomerCoupons(int customerId);

    List<Coupon> getCustomerCouponsByCategory(int customerId, String category);

    List<Coupon> getCustomerCouponsByPriceLess(int customerId, double maxPrice);

    List<Customer> getCustomerDetails(int customerId) throws CouponSystemErrors;
}
