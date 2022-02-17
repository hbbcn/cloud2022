package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *@ClassName PaymentHystrixController
 *@Description  TODO
 *@Author hqb
 *@Date 2022/2/15 14:25
 *@Version 1.0
 */

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

 /*   @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){

//        int age = 10/0;
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒后再试"  + id + "\t" + "/(ㄒoㄒ)/~~";
    }

    /**
     *上图故意制造两个异常
     * 1.int age = 10/0
     * 2.我们接收三秒钟异常，它运行五秒，超时异常
     * 当前服务不可用，做服务降级，兜底的方案都是paymentInfo_TimeOutHandler
     */

    // 下面是全局fallback
    public String payment_Global_FallbackMethod(){

        return "Global异常处理信息，请稍后再试";
    }



}

