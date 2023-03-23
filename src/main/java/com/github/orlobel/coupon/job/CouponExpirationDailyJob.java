package com.github.orlobel.coupon.job;

import java.util.Date;
import com.github.orlobel.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CouponExpirationDailyJob {
    private final CouponRepository couponRepository;

    public void run() {
        log.info("running delete expired coupons job");
        try {
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date date = dateFormat.parse("01/01/2025");
            couponRepository.deleteExpired(new Date());
//            couponRepository.deleteExpired(date);
            log.info("finished delete expired coupons job");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
