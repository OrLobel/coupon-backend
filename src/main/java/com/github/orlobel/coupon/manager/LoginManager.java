//package com.github.orlobel.manager;
//
//import com.github.orlobel.enums.ClientType;
//import com.github.orlobel.service.AdminServiceImpl;
//import com.github.orlobel.service.ClientService;
//import com.github.orlobel.service.CompanyServiceImpl;
//import com.github.orlobel.service.CustomerServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class LoginManager {
//    @Autowired
//    private final AdminServiceImpl adminService;
//    @Autowired
//    private final CustomerServiceImpl customerService;
//    @Autowired
//    private final CompanyServiceImpl companyService;
//
//    public ClientService login(String email, String password, ClientType clientType) {
//        switch (clientType) {
//            case ADMINISTRATOR:
//                return loginByClientType(adminService, email, password);
//            case CUSTOMER:
//                return loginByClientType(customerService, email, password);
//            case COMPANY:
//                return loginByClientType(companyService, email, password);
//        }
//
//        return null;
//    }
//
//    private ClientService loginByClientType(ClientService clientService, String email, String password) {
//        if (clientService.login(email, password)) {
//            return clientService;
//        }
//        return null;
//    }
//}
