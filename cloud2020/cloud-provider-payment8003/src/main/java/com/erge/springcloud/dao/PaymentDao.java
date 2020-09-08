package com.erge.springcloud.dao;

import com.erge.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int creat(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);
}
