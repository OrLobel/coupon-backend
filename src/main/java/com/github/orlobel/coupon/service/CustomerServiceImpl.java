package com.github.orlobel.coupon.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.github.orlobel.coupon.beans.Coupon;
import com.github.orlobel.coupon.beans.Customer;
import com.github.orlobel.coupon.exception.CouponSystemErrors;
import com.github.orlobel.coupon.exception.ErrorMsg;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Override
    public Customer login(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        List allCoupons = new ArrayList<Coupon>();
        allCoupons = couponRepository.findAll();
        return allCoupons;
    }

    public void purchaseCoupon(int customerId, int couponId) throws CouponSystemErrors {
        Date date = new Date(System.currentTimeMillis());
        Coupon coupon = couponRepository.getById(couponId);
        if (date.after(coupon.getEndDate()) || coupon.getAmount() <= 0) {
            throw new CouponSystemErrors(ErrorMsg.Couldnt_Add_Coupon_To_Customer);
        }

        Customer customer = customerRepository
            .findById(customerId).orElseThrow(() -> new CouponSystemErrors(ErrorMsg.Customer_Doesnt_Exists));

        if (couponRepository.couponAlreadyPurchased(customer.getId(), coupon.getId()) == 1) {
            throw new CouponSystemErrors(ErrorMsg.CUSTOMER_ALREADY_PURCHASED_COUPON);
        }
        List<Coupon> myCouponList = new ArrayList<>();
        myCouponList.addAll(customer.getCoupons());
        myCouponList.add(coupon);
        customer.setCoupons(myCouponList);
        customerRepository.save(customer);
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepository.save(coupon);
    }

//    public void purchaseCoupon(int customerId, Coupon coupon) throws CouponSystemErrors {
//        Date date = new Date(System.currentTimeMillis());
//        if (date.after(coupon.getEndDate()) || coupon.getAmount() <= 0) {
//            throw new CouponSystemErrors(ErrorMsg.Couldnt_Add_Coupon_To_Customer);
//        }
//
//        Customer customer = customerRepository
//            .findById(customerId).orElseThrow(() -> new CouponSystemErrors(ErrorMsg.Customer_Doesnt_Exists));
//
//        if (couponRepository.couponAlreadyPurchased(customer.getId(), coupon.getId()) == 1) {
//            throw new CouponSystemErrors(ErrorMsg.CUSTOMER_ALREADY_PURCHASED_COUPON);
//        }
//        List<Coupon> myCouponList = new ArrayList<>();
//        myCouponList.addAll(customer.getCoupons());
//        myCouponList.add(coupon);
//        customer.setCoupons(myCouponList);
//        customerRepository.save(customer);
//        coupon.setAmount(coupon.getAmount() - 1);
//        couponRepository.save(coupon);
//    }


    public List<Coupon> getCustomerCoupons(int customerId) {
        return customerRepository.findById(customerId).get().getCoupons();
    }

    public List<Coupon> getCustomerCouponsByCategory(int customerId, String category) {
        return couponRepository.customerCouponsAndCategory(customerId, category);
    }

    public List<Coupon> getCustomerCouponsByPriceLess(int customerId, double maxPrice) {
        return couponRepository.customerCouponsByPriceLess(customerId, maxPrice);
    }

    public List<Customer> getCustomerDetails(int customerId) throws CouponSystemErrors {

        List customerList = new ArrayList<Customer>();

        customerList.add(customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemErrors(ErrorMsg.Customer_Doesnt_Exists)));
//        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemErrors(ErrorMsg.Customer_Doesnt_Exists));
        return customerList;
    }
}
