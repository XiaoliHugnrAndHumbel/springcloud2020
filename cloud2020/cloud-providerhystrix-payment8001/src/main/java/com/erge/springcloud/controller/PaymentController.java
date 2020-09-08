package com.erge.springcloud.controller;

import com.erge.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;


    @GetMapping(value = "/payment/Hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id")Integer id){
        return paymentService.paymentInfo_ok(id);
    }


    @GetMapping(value = "/payment/Hystrix/paymentInfo_timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_timeout(id);
    }
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
       return paymentService.paymentCircuitBreaker(id);
    }
}
