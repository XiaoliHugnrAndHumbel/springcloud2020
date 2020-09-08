package com.erge.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixservice {
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "------PaymentFallbackService fall paymentInfo_Ok-----";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "------PaymentFallbackService fall paymentInfo_timeout-----";
    }
}
