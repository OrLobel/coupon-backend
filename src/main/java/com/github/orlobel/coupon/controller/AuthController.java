package com.github.orlobel.coupon.controller;

import java.util.UUID;
import com.github.orlobel.coupon.dto.UserDto;
import com.github.orlobel.coupon.manager.User;
import com.github.orlobel.coupon.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/login")
    public User login(@RequestBody UserDto userDto) {
        return authService.login(userDto);
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<Void> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        authService.logout(UUID.fromString(token));

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }
}
