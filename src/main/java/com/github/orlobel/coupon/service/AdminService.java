package com.github.orlobel.coupon.service;

import java.util.List;
import com.github.orlobel.coupon.beans.Company;
import com.github.orlobel.coupon.beans.Customer;
import com.github.orlobel.coupon.exception.CouponSystemErrors;

public interface AdminService {
    boolean login(String email, String password);

    Company addCompany(Company company) throws CouponSystemErrors;

    Company updateCompany(int companyID, Company company) throws CouponSystemErrors;

    void deleteCompany(int companyID) throws CouponSystemErrors;

    List<Company> getAllCompanies();

    List<Company> getOneCompany(int companyID) throws CouponSystemErrors;

    Customer addCustomer(Customer customer) throws CouponSystemErrors;

    Customer updateCustomer(int customerId, Customer customer) throws CouponSystemErrors;

    void deleteCustomer(int customerID) throws CouponSystemErrors;

    List<Customer> getAllCustomers();

    List<Customer> getOneCustomer(int customerID) throws CouponSystemErrors;
}
