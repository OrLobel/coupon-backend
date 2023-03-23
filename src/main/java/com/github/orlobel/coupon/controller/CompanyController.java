package com.github.orlobel.coupon.controller;

import java.util.List;
import java.util.UUID;
import com.github.orlobel.coupon.beans.Company;
import com.github.orlobel.coupon.beans.Coupon;
import com.github.orlobel.coupon.exception.CouponSystemErrors;
import com.github.orlobel.coupon.manager.TokensManager;
import com.github.orlobel.coupon.manager.User;
import com.github.orlobel.coupon.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/company", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CompanyController {
    private final TokensManager tokensManager;
    private final CompanyService companyService;

    // TODO add response status instead of responseEntity

    @PostMapping("/coupon")
    public Coupon addCoupon(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                            @RequestBody Coupon coupon) throws CouponSystemErrors {
        addCompanyToCoupon(token, coupon);
        return companyService.addCoupon(coupon);
    }

    // added response status instead of responseEntity
    @PutMapping("/coupon/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Coupon updateCoupon(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                               @PathVariable("id") int id,
                               @RequestBody Coupon coupon) throws CouponSystemErrors {
        addCompanyToCoupon(token, coupon);
        return companyService.updateCoupon(id, coupon);
    }

    private void addCompanyToCoupon(String token, Coupon coupon) {
        User user = tokensManager.getUser(UUID.fromString(token));
        Company company = Company.builder()
            .id(user.getCompanyId())
            .build();
        coupon.setCompany(company);
    }

    @DeleteMapping("/coupon/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable("id") int id) throws CouponSystemErrors {
        companyService.deleteCoupon(id);

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @GetMapping("/coupon")
    public List<Coupon> getCompanyCoupons(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws CouponSystemErrors {
        User user = tokensManager.getUser(UUID.fromString(token));
        return companyService.getCompanyCoupons(user.getCompanyId());
    }

    // TODO add RequestParam (which which is which)(i changed pathparam to requestparam
    @GetMapping("/coupon/category/{category}")
    public List<Coupon> getCompanyCouponsByCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                    @PathVariable("category") String category) throws CouponSystemErrors {
        User user = tokensManager.getUser(UUID.fromString(token));
        return companyService.getCompanyCouponsByCategory(user.getCompanyId(), category);
    }

    @GetMapping("/coupon/max-price/{maxPrice}")
    public List<Coupon> getCompanyCouponsLessThan(@PathVariable("maxPrice") double maxPrice) {
        return companyService.getCompanyCouponsLessThan(maxPrice);
    }

    @GetMapping("/companyinfo")
    public List<Company> getCompanyDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = tokensManager.getUser(UUID.fromString(token));
//        Company companytest = companyService.getCompanyDetails(user.getCompanyId());
//        return companytest;
        return companyService.getCompanyDetails(user.getCompanyId());

    }
}
