package com.github.orlobel.coupon.service;

import java.util.UUID;
import com.github.orlobel.coupon.beans.Company;
import com.github.orlobel.coupon.beans.Customer;
import com.github.orlobel.coupon.dto.UserDto;
import com.github.orlobel.coupon.enums.ClientType;
import com.github.orlobel.coupon.manager.TokensManager;
import com.github.orlobel.coupon.manager.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokensManager tokensManager;
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    @PostMapping(path = "/login")
    public User login(@RequestBody UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        boolean adminLogin = adminService.login(email, password);
        if (adminLogin) {
            User user = User.builder()
                .email(email)
                .clientType(ClientType.ADMINISTRATOR)
                .build();

            tokensManager.createToken(user);

            return user;
        }

        Company company = companyService.login(email, password);
        if (company != null) {
            User user = User.builder()
                .email(email)
                .clientType(ClientType.COMPANY)
                .companyId(company.getId())
                .build();
            tokensManager.createToken(user);

            return user;
        }

        Customer customer = customerService.login(email, password);
        if (customer != null) {
            User user = User.builder()
                .email(email)
                .clientType(ClientType.CUSTOMER)
                .customerId(customer.getId())
                .build();
            tokensManager.createToken(user);

            return user;
        }

        throw new RuntimeException("TODO");
    }

    public void logout(UUID token) {
        tokensManager.removeToken(token);
    }
}
