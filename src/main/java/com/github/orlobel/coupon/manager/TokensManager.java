package com.github.orlobel.coupon.manager;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokensManager {
    private final Map<UUID, User> tokens = new ConcurrentHashMap<>();

    public User createToken(User user) {
        UUID token = UUID.randomUUID();

        user.setToken(token);

        tokens.put(token, user);

        return user;
    }

    public User getUser(UUID token) {
        return tokens.get(token);
    }

    public void removeToken(UUID token) {
        this.tokens.remove(token);
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void deleteExpiredTokenOver30Minutes() {
        this.tokens.entrySet().removeIf(it -> System.currentTimeMillis() - it.getValue().getCreatedAt() > 30 * 60 * 1000);
    }
}
