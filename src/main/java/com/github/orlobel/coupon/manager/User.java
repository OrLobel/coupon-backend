package com.github.orlobel.coupon.manager;

import java.util.UUID;
import com.github.orlobel.coupon.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
    private UUID token;
    private String email;
    private ClientType clientType;
    @Builder.Default
    private long createdAt = System.currentTimeMillis();
    @Builder.Default
    private Integer companyId = null;
    @Builder.Default
    private Integer customerId = null;
}
