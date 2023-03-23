package com.github.orlobel.coupon.service;

import java.util.List;
import com.github.orlobel.coupon.beans.Company;
import com.github.orlobel.coupon.beans.Coupon;
import com.github.orlobel.coupon.exception.CouponSystemErrors;

public interface CompanyService {

    Company login(String email, String password);

    Coupon addCoupon(Coupon coupon) throws CouponSystemErrors;

    Coupon updateCoupon(int id, Coupon coupon) throws CouponSystemErrors;

    void deleteCoupon(int couponID) throws CouponSystemErrors;

    List<Coupon> getCompanyCoupons(int id) throws CouponSystemErrors;

    List<Coupon> getCompanyCouponsByCategory(int id, String category) throws CouponSystemErrors;

    List<Coupon> getCompanyCouponsLessThan(double maxPrice);

    List<Company> getCompanyDetails(int id);

}
