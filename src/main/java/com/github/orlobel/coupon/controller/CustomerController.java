package com.github.orlobel.coupon.controller;

import java.util.List;
import java.util.UUID;
import com.github.orlobel.coupon.beans.Coupon;
import com.github.orlobel.coupon.beans.Customer;
import com.github.orlobel.coupon.exception.CouponSystemErrors;
import com.github.orlobel.coupon.manager.TokensManager;
import com.github.orlobel.coupon.manager.User;
import com.github.orlobel.coupon.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    private final TokensManager tokensManager;
    private final CustomerService customerService;

    @GetMapping(path = "/purchase/{couponId}")
    public void purchaseCoupon(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                               @PathVariable("couponId") int couponId) throws CouponSystemErrors {
        User user = tokensManager.getUser(UUID.fromString(token));
        customerService.purchaseCoupon(user.getCustomerId(), couponId);
    }

//    @GetMapping(path = "/purchase/{couponId}")
//    public void purchaseCoupon(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
//                               @PathVariable("couponId") int couponId) throws CouponSystemErrors {
//        User user = tokensManager.getUser(UUID.fromString(token));
//        customerService.purchaseCoupon(user.getCustomerId(), couponId);
//    }
//

    @GetMapping("/coupon")
    public List<Coupon> getCustomerCoupons(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = tokensManager.getUser(UUID.fromString(token));
        return customerService.getCustomerCoupons(user.getCustomerId());
    }

    @GetMapping("/all/coupon")
    public List<Coupon> getAllCoupons() {

        return customerService.getAllCoupons();
    }

    // TODO Request param which which is which
    @GetMapping("/coupon/category/{category}")
    public List<Coupon> getCustomerCouponsByCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                     @PathVariable("category") String category) {
        User user = tokensManager.getUser(UUID.fromString(token));
        return customerService.getCustomerCouponsByCategory(user.getCustomerId(), category);
    }

    @GetMapping("/coupon/max-price/{maxPrice}")
    public List<Coupon> getCustomerCouponsByPriceLess(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                      @PathVariable("maxPrice") double maxPrice) {
        User user = tokensManager.getUser(UUID.fromString(token));
        return customerService.getCustomerCouponsByPriceLess(user.getCustomerId(), maxPrice);
    }

    @GetMapping("/info")
    public List<Customer> getCustomerDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws CouponSystemErrors {
        User user = tokensManager.getUser(UUID.fromString(token));
        return customerService.getCustomerDetails(user.getCustomerId());
    }

//    @GetMapping("/info")
//    public Customer getCustomerDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws CouponSystemErrors {
//        User user = tokensManager.getUser(UUID.fromString(token));
//        return customerService.getCustomerDetails(user.getCustomerId());
//    }
}
