package com.github.orlobel.coupon.exception;

public class CouponSystemErrors extends Exception {
    public CouponSystemErrors(ErrorMsg errorMsg) {
        super(errorMsg.getMessage());
    }
}
