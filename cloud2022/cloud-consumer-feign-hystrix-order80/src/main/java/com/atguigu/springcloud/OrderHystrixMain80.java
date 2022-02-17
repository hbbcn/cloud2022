package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *@ClassName OrderHystrixMain80
 *@Description  TODO
 *@Author hqb
 *@Date 2022/2/15 14:05
 *@Version 1.0
 */

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderHystrixMain80{
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}



