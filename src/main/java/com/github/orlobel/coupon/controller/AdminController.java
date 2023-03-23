package com.github.orlobel.coupon.controller;

import java.util.List;
import com.github.orlobel.coupon.beans.Company;
import com.github.orlobel.coupon.beans.Customer;
import com.github.orlobel.coupon.dto.CompanyDto;
import com.github.orlobel.coupon.exception.CouponSystemErrors;
import com.github.orlobel.coupon.mapper.CompanyMapper;
import com.github.orlobel.coupon.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admin")//, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {
    private final AdminService adminService;
    private final CompanyMapper companyMapper;

    @PostMapping(path = "/company")
    public Company addCompany(@RequestBody Company company) throws CouponSystemErrors {
        return adminService.addCompany(company);
    }

    @PutMapping(path = "/company/{id}")
    public Company updateCompany(@PathVariable("id") int id, @RequestBody Company company) throws CouponSystemErrors {
        return adminService.updateCompany(id, company);
    }

    @DeleteMapping(path = "/company/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") int id) throws CouponSystemErrors {
        adminService.deleteCompany(id);

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }
//
//    @DeleteMapping(path = "/company/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteCompany(@PathVariable("id") Integer id) throws CouponSystemErrors {
//        adminService.deleteCompany(id);
//
//    }

    @GetMapping("/company")
    public List<CompanyDto> getAllCompanies() {
        List<CompanyDto> bla;
        try {
            List<Company> allCompanies = adminService.getAllCompanies();
            bla = companyMapper.toDtoList(allCompanies);
            return bla;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
//        return adminService.getAllCompanies();
    }

    @GetMapping("/company/{id}")
    public List<Company> getOneCompany(@PathVariable("id") int id) throws CouponSystemErrors {
        return adminService.getOneCompany(id);
    }

    @PostMapping(path = "/customer")
    public Customer addCustomer(@RequestBody Customer customer) throws CouponSystemErrors {
        return adminService.addCustomer(customer);

//        return ResponseEntity
//            .status(HttpStatus.NO_CONTENT)
//            .build();
    }

    @PutMapping(path = "/customer/{id}")
    public Customer updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) throws CouponSystemErrors {
        return adminService.updateCustomer(id, customer);

//        return ResponseEntity
//            .status(HttpStatus.NO_CONTENT)
//            .build();
    }

    @DeleteMapping(path = "/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) throws CouponSystemErrors {
        adminService.deleteCustomer(id);

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @GetMapping(path = "/customer")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public List<Customer> getOneCustomer(@PathVariable("id") int id) throws CouponSystemErrors {
        return adminService.getOneCustomer(id);
    }
}
