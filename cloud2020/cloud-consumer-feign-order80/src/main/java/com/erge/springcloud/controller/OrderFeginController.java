package com.erge.springcloud.controller;

import com.erge.springcloud.entities.CommonResult;
import com.erge.springcloud.entities.Payment;
import com.erge.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Priority;
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeginController {
    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
            return paymentFeignService.getPaymentById(id);
    }
    @GetMapping(value = "/consumer/payment/timeout")
    public String timeout(){
        return paymentFeignService.timeout();
    }

}
