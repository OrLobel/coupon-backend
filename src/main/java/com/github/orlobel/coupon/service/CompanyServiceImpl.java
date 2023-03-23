package com.github.orlobel.coupon.service;

import java.util.ArrayList;
import java.util.List;
import com.github.orlobel.coupon.beans.Company;
import com.github.orlobel.coupon.beans.Coupon;
import com.github.orlobel.coupon.exception.CouponSystemErrors;
import com.github.orlobel.coupon.exception.ErrorMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService {
    public Company login(String email, String password) {
        return companyRepository.findByEmailAndPassword(email, password);
    }


    public Coupon addCoupon(Coupon coupon) throws CouponSystemErrors {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), coupon.getCompany().getId())) {
            throw new CouponSystemErrors(ErrorMsg.Coupon_Already_Exists);
        }

        return couponRepository.save(coupon);
    }

    public Coupon updateCoupon(int id, Coupon coupon) throws CouponSystemErrors {
        Coupon couponToUpdate = couponRepository.findById(id).orElseThrow(() -> new CouponSystemErrors(ErrorMsg.Coupon_Doesnt_Exists));

        if (!coupon.getCompany().getId().equals(couponToUpdate.getCompany().getId())) {
            throw new CouponSystemErrors(ErrorMsg.Coupon_ID_DOESNT_FIT);
        }

        couponToUpdate.setAmount(coupon.getAmount());
        couponToUpdate.setCategory(coupon.getCategory());
        couponToUpdate.setDescription(coupon.getDescription());
        couponToUpdate.setStartDate(coupon.getStartDate());
        couponToUpdate.setEndDate(coupon.getEndDate());
        couponToUpdate.setImage(coupon.getImage());
        couponToUpdate.setAmount(coupon.getAmount());
        couponToUpdate.setTitle(coupon.getTitle());
        couponToUpdate.setPrice(coupon.getPrice());

        return couponRepository.save(couponToUpdate);

    }

    public void deleteCoupon(int couponID) throws CouponSystemErrors {
        if (!couponRepository.existsById(couponID))
            throw new CouponSystemErrors(ErrorMsg.Coupon_Doesnt_Exists);
        couponRepository.deleteCouponFromCustomer(couponID);
        couponRepository.deleteById(couponID);


    }

    public List<Coupon> getCompanyCoupons(int id) throws CouponSystemErrors {
        if (!companyRepository.existsById(id))
            throw new CouponSystemErrors(ErrorMsg.COMPANY_DOES_NOT_EXIST);
        return couponRepository.findAllByCompanyId(id);
    }

    public List<Coupon> getCompanyCouponsByCategory(int id, String category) throws CouponSystemErrors {
        if (!companyRepository.existsById(id))
            throw new CouponSystemErrors(ErrorMsg.COMPANY_DOES_NOT_EXIST);
        return couponRepository.findByCompanyIdAndCategory(id, category);
    }

    //    public List<Coupon> getCompanyCouponsByCategory(int id, Category category) throws CouponSystemErrors {
//        if (!companyRepository.existsById(id))
//            throw new CouponSystemErrors(ErrorMsg.COMPANY_DOES_NOT_EXIST);
//        return couponRepository.findByCompanyIdAndCategory(id, category.name());
//    }
//TODO add company id and change query
    public List<Coupon> getCompanyCouponsLessThan(double maxPrice) {
        return couponRepository.findByPriceLessThan(maxPrice);
    }

    // this should be changed when used UUID
//    public Company getCompanyDetails(int id) {
//        return companyRepository.getById(id);
//    }


    public List<Company> getCompanyDetails(int id) {
        List<Company> companyList = new ArrayList();
        companyList.add(companyRepository.findById(id).orElseThrow(RuntimeException::new));
        return companyList;
    }

}
