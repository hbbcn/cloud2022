package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 *@ClassName PaymentFallbackService
 *@Description  TODO
 *@Author hqb
 *@Date 2022/2/16 21:55
 *@Version 1.0
 */

@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "---PaymentFallbackService fall paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService fall paymentInfo_TimeOut";
    }
}

