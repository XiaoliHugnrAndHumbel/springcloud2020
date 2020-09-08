package com.erge.springcloud.service;

import com.erge.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {
    public String paymentInfo_ok(Integer id);
    public String paymentInfo_timeout(Integer id);
    public String paymentInfo_TimeOutHandler(Integer id);
    public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
