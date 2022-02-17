package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;


/**
 *@ClassName OrderMain80
 *@Description  TODO
 *@Author hqb
 *@Date 2022/2/12 13:48
 *@Version 1.0
 */

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration= MySelfRule.class)
public class OrderMain80{

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}

