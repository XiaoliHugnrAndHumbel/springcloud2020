package com.erge.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentHystrixservice {
    @GetMapping(value = "/payment/Hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id")Integer id);
    @GetMapping(value = "/payment/Hystrix/paymentInfo_timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id);
}
