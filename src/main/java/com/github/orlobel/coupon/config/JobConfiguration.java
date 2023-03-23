package com.github.orlobel.coupon.config;

import com.github.orlobel.coupon.job.CouponExpirationDailyJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Slf4j
public class JobConfiguration {
    private final CouponExpirationDailyJob couponExpirationDailyJob;

    @Autowired
    public JobConfiguration(CouponExpirationDailyJob couponExpirationDailyJob) {
        this.couponExpirationDailyJob = couponExpirationDailyJob;
    }

    //    @Scheduled(cron = "0 1 1 * * ?")
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void couponDailyJob() {
        log.info("Started daily job");
        couponExpirationDailyJob.run();
    }
}
