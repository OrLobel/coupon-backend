package com.github.orlobel.coupon.repository;

import java.util.Date;
import java.util.List;
import com.github.orlobel.coupon.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    @Query("delete from Coupon c where c.endDate < :date")
    @Modifying
    @Transactional
    void deleteExpired(@Param("date") Date date);

    boolean existsByTitleAndCompanyId(String title, int companyId);

    List<Coupon> findAllByCompanyId(int companyID);

    @Query(value = "SELECT * FROM `coupon-system`.coupons where company_id=:companyId and category=:categoryName", nativeQuery = true)
    List<Coupon> findByCompanyIdAndCategory(@Param("companyId") int companyId, @Param("categoryName") String categoryName);

    List<Coupon> findByCategory(String category);

    List<Coupon> findByPriceLessThan(double price);

    @Query(value = "SELECT * FROM `coupon-system`.coupons where id in (select coupons_id from `coupon-system`.customers_coupons where customer_id=:customerId )and category=:categoryName", nativeQuery = true)
    List<Coupon> customerCouponsAndCategory(@Param("customerId") int customerId, @Param("categoryName") String categoryName);

    @Query(value = "SELECT * FROM `coupon-system`.coupons where id in (select coupons_id from `coupon-system`.customers_coupons where customer_id=:customerId )and price<=:maxPrice", nativeQuery = true)
    List<Coupon> customerCouponsByPriceLess(@Param("customerId") int customerId, @Param("maxPrice") double maxPrice);

    @Query(value = "delete FROM `coupon-system`.customers_coupons where coupons_id in (select id from `coupon-system`.coupons where company_id=:companyId)", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteCustomerCouponsRelatedToCompany(@Param("companyId") int companyId);

    @Query(value = "delete FROM `coupon-system`.customers_coupons where coupons_id=:couponId", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteCouponFromCustomer(@Param("couponId") int couponId);

    @Query(value = "select exists (SELECT * FROM `coupon-system`.customers_coupons where (customer_id=:customerId and coupons_id=:couponID)) as res", nativeQuery = true)
    int couponAlreadyPurchased(@Param("customerId") int customerId, @Param("couponID") int couponID);


}
