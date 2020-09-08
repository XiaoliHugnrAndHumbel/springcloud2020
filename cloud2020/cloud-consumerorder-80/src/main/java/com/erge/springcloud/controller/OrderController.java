package com.erge.springcloud.controller;

import com.erge.springcloud.entities.CommonResult;
import com.erge.springcloud.entities.Payment;
import com.erge.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.PrimitiveIterator;

@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL="http://localhost:8001";
public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    /**
     * restTemplate.postForObject 返回一个服务器得json信息
     *
     * */
    @GetMapping("/consumer/payment/creat")
    public CommonResult<Payment> creat(Payment payment){
        log.info("80新建");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/creat",payment,CommonResult.class);
    }
    @GetMapping(value="/consumer/payment/get/{id}")
    public CommonResult<Payment> getParment(@PathVariable("id")Long id){
        log.info("80查询");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    /**
     * restTemplate.getForEntity  获取一个Entity对象，可以得到跟细化的服务器信息
     *
     * */
    @GetMapping(value="/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getParment2(@PathVariable("id")Long id){
        log.info("80查询");
        ResponseEntity<CommonResult> resultResponseEntity=restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if(resultResponseEntity.getStatusCode().is2xxSuccessful()){
            return  resultResponseEntity.getBody();
        }
        else{
            return  new CommonResult<>(444,"操作失败");
        }

    }
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances==null||instances.size()<=0){
            return null;
        }

        ServiceInstance serviceInstance=loadBalancer.instance(instances);
        URI uri=serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
