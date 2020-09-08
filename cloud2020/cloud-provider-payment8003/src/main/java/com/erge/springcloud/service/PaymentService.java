package com.erge.springcloud.service;

import com.erge.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int creat(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);
}
