package com.github.orlobel.coupon.auth;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.github.orlobel.coupon.enums.ClientType;
import com.github.orlobel.coupon.manager.TokensManager;
import com.github.orlobel.coupon.manager.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final TokensManager tokensManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String contextPath = request.getServletPath();
        if (contextPath.equals("/auth/login")) {
            return true;
        }

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            forbidden(response);
            return false;
        }

        User user = tokensManager.getUser(UUID.fromString(token));

        if (user == null) {
            forbidden(response);
            return false;
        }

        ClientType clientType = user.getClientType();
        if (contextPath.startsWith("/api/admin") && !clientType.equals(ClientType.ADMINISTRATOR)) {
            forbidden(response);
            return false;
        } else if (contextPath.startsWith("/api/company") && !clientType.equals(ClientType.COMPANY)) {
            forbidden(response);
            return false;
        } else if (contextPath.startsWith("/api/customer") && !clientType.equals(ClientType.CUSTOMER)) {
            forbidden(response);
            return false;
        }

        return true;
    }

    private void forbidden(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // TODO: add error body
    }
}
