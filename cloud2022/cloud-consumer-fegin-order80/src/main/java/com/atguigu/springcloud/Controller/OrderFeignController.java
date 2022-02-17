package com.atguigu.springcloud.Controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *@ClassName OrderFeignController
 *@Description  TODO
 *@Author hqb
 *@Date 2022/2/14 14:00
 *@Version 1.0
 */
@RestController
@Slf4j
public class OrderFeignController{


    @Autowired
    private PaymentFeignService paymentFeignService;


    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") int id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon 客户端默认等待1秒钟

        return paymentFeignService.paymentFeignTimeout();
    }


}

