package com.erge.springcloud.controller;

import com.erge.springcloud.service.PaymentHystrixservice;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")//全局的错误回调，
public class OrderHystrixController {
  @Resource
    private PaymentHystrixservice paymentHystrixservice;

    @GetMapping(value = "/payment/Hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id")Integer id){
        return paymentHystrixservice.paymentInfo_Ok(id);
    }


    @GetMapping(value = "/payment/Hystrix/paymentInfo_timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })//在有特定的错误回调时，用自定的，否则就使用全局的
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        return paymentHystrixservice.paymentInfo_timeout(id);
    }
    @HystrixCommand
    @GetMapping(value = "/payment/Hystrix/paymentInfo_error")
    public String paymentInfo_error(){
        int a=10/0;
        return "全局的回调测试";
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }
    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
