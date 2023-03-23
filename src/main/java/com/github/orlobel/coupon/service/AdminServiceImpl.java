package com.github.orlobel.coupon.service;

import java.util.ArrayList;
import java.util.List;
import com.github.orlobel.coupon.beans.Company;
import com.github.orlobel.coupon.beans.Customer;
import com.github.orlobel.coupon.exception.CouponSystemErrors;
import com.github.orlobel.coupon.exception.ErrorMsg;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    public Company addCompany(Company company) throws CouponSystemErrors {
        if (companyRepository.existsByNameAndEmail(company.getName(), company.getEmail())) {
            throw new CouponSystemErrors(ErrorMsg.Comapany_Email_Name_Exist);
        }

        return companyRepository.save(company);
    }


    public Company updateCompany(int companyID, Company company) throws CouponSystemErrors {
        Company companyToUpdate = companyRepository.findById(companyID).
            orElseThrow((() -> new CouponSystemErrors(ErrorMsg.COMPANY_DOES_NOT_EXIST)));
        companyToUpdate.setCoupons(company.getCoupons());
        companyToUpdate.setName(company.getName());
        companyToUpdate.setEmail(company.getEmail());
        companyToUpdate.setPassword(company.getPassword());

        return companyRepository.save(companyToUpdate);
    }

    //deletion of coupons works through the Cascade.
    public void deleteCompany(int companyID) throws CouponSystemErrors {
        if (!companyRepository.existsById(companyID))
            throw new CouponSystemErrors(ErrorMsg.COMPANY_DOES_NOT_EXIST);
        couponRepository.deleteCustomerCouponsRelatedToCompany(companyID);
        companyRepository.deleteById(companyID);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Company> getOneCompany(int companyID) throws CouponSystemErrors {
        List companyList = new ArrayList<Company>();
//        if(companyRepository.findById(companyID)==null)
//        {
//            throw new ErrorMsg.COMPANY_DOES_NOT_EXIST;
//        }
        companyList.add(companyRepository.findById(companyID).orElseThrow((() -> new CouponSystemErrors(ErrorMsg.COMPANY_DOES_NOT_EXIST))));
        return companyList;
//        return companyRepository.findById(companyID).orElseThrow((() -> new CouponSystemErrors(ErrorMsg.COMPANY_DOES_NOT_EXIST)));
    }

    // customer
    public Customer addCustomer(Customer customer) throws CouponSystemErrors {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemErrors(ErrorMsg.Customer_Already_Exists);
        }
        return customerRepository.save(customer);

    }

    public Customer updateCustomer(int customerId, Customer customer) throws CouponSystemErrors {
        Customer customerToChange = customerRepository.findById(customerId).
            orElseThrow(() -> new CouponSystemErrors(ErrorMsg.Customer_Doesnt_Exists));
        customerToChange.setCoupons(customer.getCoupons());
        customerToChange.setEmail(customer.getEmail());
        customerToChange.setFirstName(customer.getFirstName());
        customerToChange.setLastName(customer.getLastName());
        customerToChange.setPassword(customer.getPassword());
        customerRepository.save(customerToChange);
        return customerToChange;
    }

    // coupons delete works through cascade
    public void deleteCustomer(int customerID) throws CouponSystemErrors {
        if (!customerRepository.existsById(customerID))
            throw new CouponSystemErrors(ErrorMsg.Customer_Doesnt_Exists);
        customerRepository.deleteById(customerID);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getOneCustomer(int customerID) throws CouponSystemErrors {
        List listToReturn = new ArrayList<Customer>();
        listToReturn.add(customerRepository.findById(customerID).orElseThrow(() -> new CouponSystemErrors(ErrorMsg.Customer_Doesnt_Exists)));
        return listToReturn;
//        customerRepository.findById(customerID).orElseThrow(() -> new CouponSystemErrors(ErrorMsg.Customer_Doesnt_Exists));
    }
}
