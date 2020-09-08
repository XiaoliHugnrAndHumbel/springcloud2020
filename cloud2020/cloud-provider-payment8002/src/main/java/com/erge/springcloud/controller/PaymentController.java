package com.erge.springcloud.controller;

import com.erge.springcloud.entities.CommonResult;
import com.erge.springcloud.entities.Payment;
import com.erge.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value="/payment/creat")
    public CommonResult creat(@RequestBody Payment payment){
        int result=paymentService.creat(payment);
        log.info("************插入结果:"+result);
        if (result>0){
            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据失败,serverPort:"+serverPort,null);
        }
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment payment =paymentService.getPaymentById(id);
        log.info("************插入结果:"+payment+"11111");
        if (payment!=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应的记录，查询id："+id+"  serverPort:"+serverPort,null);
        }
    }

    @GetMapping(value="/payment/discovery")
    public Object discovery(){
        //获取自己服务器的信息
        List<String> services=discoveryClient.getServices();
        for (String str:services){
            log.info("********element"+str);
        }

        //获取这个服务的所有服务器信息
        List<ServiceInstance> instances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance info:instances){
            log.info(info.getInstanceId()+"\t"+info.getHost()+"\t"+info.getPort()+"\t"+info.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    @GetMapping(value = "/payment/timeout")
    public String timeout(){
        try{
            TimeUnit.SECONDS.sleep(3l);
        }catch (InterruptedException e){

        }
        return serverPort;
    }
}
