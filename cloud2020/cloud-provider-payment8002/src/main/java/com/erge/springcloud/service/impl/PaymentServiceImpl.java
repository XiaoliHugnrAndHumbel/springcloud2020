package com.erge.springcloud.service.impl;

import com.erge.springcloud.dao.PaymentDao;
import com.erge.springcloud.entities.Payment;
import com.erge.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int creat(Payment payment){return paymentDao.creat(payment);};
    public Payment getPaymentById(Long id){return paymentDao.getPaymentById(id);};
}
