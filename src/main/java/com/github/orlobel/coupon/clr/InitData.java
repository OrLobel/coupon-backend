package com.github.orlobel.coupon.clr;

import com.github.orlobel.coupon.art.Art;
import com.github.orlobel.coupon.beans.Company;
import com.github.orlobel.coupon.beans.Coupon;
import com.github.orlobel.coupon.beans.Customer;
import com.github.orlobel.coupon.enums.Category;
import com.github.orlobel.coupon.exception.CouponSystemErrors;
import com.github.orlobel.coupon.exception.ErrorMsg;
import com.github.orlobel.coupon.repository.CompanyRepository;
import com.github.orlobel.coupon.repository.CouponRepository;
import com.github.orlobel.coupon.repository.CustomerRepository;
import com.github.orlobel.coupon.service.AdminServiceImpl;
import com.github.orlobel.coupon.service.CompanyServiceImpl;
import com.github.orlobel.coupon.service.CustomerServiceImpl;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final AdminServiceImpl adminService;
    private final CompanyServiceImpl companyService;
    private final CustomerServiceImpl customerService;

    @Autowired
    public InitData(CouponRepository couponRepository,
                    CompanyRepository companyRepository,
                    CustomerRepository customerRepository,
                    AdminServiceImpl adminService,
                    CompanyServiceImpl companyService, CustomerServiceImpl customerService) {
        this.couponRepository = couponRepository;
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.adminService = adminService;
        this.companyService = companyService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        runInitData();
        serviceTest();
        System.out.println("---");
        System.out.println("Finished init");
        System.out.println("---");
    }

    private void runInitData() throws CouponSystemErrors {
        System.out.println(Art.Initiating_Data.getAsciiArt());
        Company company1 = Company.builder().name("company1")
            .email("company1@email.com")
            .password("1234")
            .build();
        Company company2 = Company.builder().name("company2")
            .email("company2@email.com")
            .password("1234")
            .build();
        Company company3 = Company.builder().name("company3")
            .email("company3@email.com")
            .password("1234")
            .build();
        Company company4 = Company.builder().name("company4")
            .email("company4@email.com")
            .password("1234")
            .build();
        Company company5 = Company.builder().name("company5")
            .email("company5@email.com")
            .password("1234")
            .build();

        companyRepository.saveAll(Arrays.asList(company1, company2, company3, company4, company5));
        companyRepository.findAll().forEach(System.out::println);
        System.out.println("______________________________________");
        System.out.println("______________________________________");
        System.out.println("______________________________________");
        System.out.println("______________________________________");


        Customer customer1 = Customer.builder().firstName("name1").lastName("last1").email("customer1@email.com").password("1234").build();
        Customer customer2 = Customer.builder().firstName("name2").lastName("last2").email("customer2@email.com").password("1234").build();
        Customer customer3 = Customer.builder().firstName("name3").lastName("last3").email("customer3@email.com").password("1234").build();
        Customer customer4 = Customer.builder().firstName("name4").lastName("last4").email("customer4@email.com").password("1234").build();
        Customer customer5 = Customer.builder().firstName("name5").lastName("last5").email("customer5@email.com").password("1234").build();
        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3, customer4, customer5));
        customerRepository.findAll().forEach(System.out::println);
        System.out.println("______________________________________");
        System.out.println("______________________________________");
        System.out.println("______________________________________");
        System.out.println("______________________________________");
        Coupon coupon1 = Coupon.builder().company(company1).category(Category.Electricity).title("title1").description("description1").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(20).image("www.image1.com").build();
        Coupon coupon2 = Coupon.builder().company(company2).category(Category.Electricity).title("title2").description("description2").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(20).image("www.image2.com").build();
        Coupon coupon3 = Coupon.builder().company(company3).category(Category.Food).title("title1").description("description3").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(30).image("www.image3.com").build();
        Coupon coupon4 = Coupon.builder().company(company1).category(Category.Vacation).title("title4").description("description4").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(30).image("www.image4.com").build();
        Coupon coupon5 = Coupon.builder().company(company5).category(Category.Restaurant).title("title5").description("description5").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(30).image("www.image5.com").build();
        Coupon coupon6 = Coupon.builder().company(company2).category(Category.Restaurant).title("title6").description("description6").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(30).image("www.image6.com").build();
        Coupon coupon7 = Coupon.builder().company(company4).category(Category.Restaurant).title("title7").description("description7").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(30).image("www.image7.com").build();


        couponRepository.saveAll(Arrays.asList(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6, coupon7));
        couponRepository.findAll().forEach(System.out::println);

        System.out.println("______________________________________");
        System.out.println("______________________________________");
        System.out.println("______________________________________");
        System.out.println("______________________________________");
        System.out.println(couponRepository.findById(4));

        Coupon newcoupon = couponRepository.findById(coupon4.getId()).orElseThrow(() -> new CouponSystemErrors(ErrorMsg.FORBIDDEN));
        System.out.println(newcoupon);
        System.out.println("Testing price query");
        System.out.println(couponRepository.findByPriceLessThan(21));
        System.out.println(Art.Finished_Initiating_Data.getAsciiArt());
    }

    private void serviceTest() throws Exception {
        System.out.println(Art.Admin_Service.getAsciiArt());
        System.out.println(adminService.login("admin@admin.com", "admin"));
        Company company6 = Company.builder()
            .name("company6")
            .email("company6@email.com")
            .password("1234")
            .build();
        Company company7 = Company.builder()
            .name("company1")
            .email("company1@email.com")
            .password("1234")
            .build();
        adminService.addCompany(company6);

        System.out.println(adminService.getOneCompany(company6.getId()));
        company6.setName("changed name");
        company6.setEmail("new email@mail.com");
        adminService.updateCompany(company6.getId(), company6);
        System.out.println(adminService.getOneCompany(company6.getId()));

        System.out.println(adminService.getAllCompanies());
        // delete company
        adminService.deleteCompany(4);
        System.out.println(adminService.getAllCompanies());
        System.out.println("__________________________________");
        System.out.println("__________________________________");
        System.out.println("__________________________________");
        System.out.println("__________________________________");
        // Customer Checking
        Customer customer6 = Customer.builder().firstName("name6").lastName("last6").email("customer6@email.com").password("1234").build();
        adminService.addCustomer(customer6);
        System.out.println(adminService.getOneCustomer(2));
        System.out.println(adminService.getAllCustomers());
        customer6.setFirstName("changed name");
        customer6.setEmail("newmail@mail.com");
        adminService.updateCustomer(customer6.getId(), customer6);
        System.out.println(adminService.getOneCustomer(customer6.getId()));
        adminService.deleteCustomer(4);
        System.out.println(adminService.getAllCustomers());


        System.out.println(Art.Company_Service.getAsciiArt());
        //creating coupons and checking login
        Coupon coupon22 = Coupon.builder().company(company6).category(Category.Restaurant).title("title22").description("description22").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(20).image("www.image22.com").build();
        Coupon coupon33 = Coupon.builder().company(company6).category(Category.Electricity).title("title33").description("description33").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(20).image("www.image33.com").build();
        Coupon coupon44 = Coupon.builder().company(company6).category(Category.Vacation).title("title44").description("description44").startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(5))).amount(5).price(20).image("www.image44.com").build();

        System.out.println(companyService.login("company1@email.com", "1234"));
        companyService.addCoupon(coupon22);
        companyService.addCoupon(coupon33);
        companyService.addCoupon(coupon44);
        coupon33.setTitle("updated coupon");
        System.out.println(coupon33.getId());
        companyService.updateCoupon(coupon33.getId(), coupon33);

        companyService.deleteCoupon(2);
//      making errors because im deleting a company then adding coupons

        System.out.println("----------------------");
//        System.out.println(companyService.getCompanyCoupons());
//        System.out.println(companyService.getCompanyCoupons());
//        System.out.println(companyService.getCompanyCouponsByCategory(, Category.Electricity));
//        System.out.println(couponRepository.findByCompanyIdAndCategory(company6.getId(),"electricity"));
        System.out.println(companyService.getCompanyCouponsLessThan(19));
//        System.out.println(companyService.getCompanyDetails());
        System.out.println("______________________");
        System.out.println("______________________");
        System.out.println("______________________");
        System.out.println("______________________");
        System.out.println("______________________");
        System.out.println("______________________");
        System.out.println(Art.Customer_Service.getAsciiArt());

        Customer customer11 = Customer.builder().firstName("name11").lastName("last11").email("customer11@email.com").password("1234").build();
        Customer customer22 = Customer.builder().firstName("name22").lastName("last22").email("customer22@email.com").password("1234").build();
        Customer customer33 = Customer.builder().firstName("name22").lastName("last22").email("customer33@email.com").password("1234").build();
        adminService.addCustomer(customer11);
        adminService.addCustomer(customer22);
        adminService.addCustomer(customer33);
        System.out.println(customerService.login("customer22@email.com", "1234"));
//        System.out.println(customerService.getCustomerDetails());
//        customerService.purchaseCoupon(coupon22, customer22);
//        customerService.purchaseCoupon(coupon33, customer22);
//        customerService.purchaseCoupon(coupon44,customer22);
//        System.out.println(customerService.getCustomerCoupons());
        System.out.println("----");
        System.out.println("----");
        System.out.println("----");
        System.out.println("----");
//        System.out.println(customerService.getCustomerCouponsByCategory(, Category.Vacation));
//        System.out.println(customerService.getCustomerCouponsByPriceLess(, 21));
        customerRepository.deleteById(7);
        System.out.println(Art.Service_Layer_Finished.getAsciiArt());
        System.out.println("-------------------------");
        System.out.println("-------------------------");

        System.out.println("Testing some stackoverflow");
        System.out.println(adminService.getAllCompanies());
        System.out.println("Testing more issues");
        Coupon couponRepositoryById = couponRepository.getById(1);
        System.out.println(couponRepository.findById(3));
        System.out.println(couponRepositoryById);
        System.out.println(companyService.getCompanyDetails(1));


    }


    //    private void loginManager() throws CouponSystemErrors {
//
//        System.out.println(Art.login_Manager.getAsciiArt());
//        AdminServiceImpl adminService = (AdminServiceImpl) loginManager.login("admin@admin.com","admin", ClientType.ADMINISTRATOR);
//        System.out.println(adminService.getAllCompanies());
//        CustomerServiceImpl customerService = (CustomerServiceImpl) loginManager.login("customer22@email.com","1234",ClientType.CUSTOMER);
//        System.out.println(customerService.getCustomerDetails());
//        CompanyServiceImpl companyService = (CompanyServiceImpl) loginManager.login("new email@mail.com","1234",ClientType.COMPANY);
//        System.out.println(companyService.getCompanyCoupons());
//
//
//
//    }
}

