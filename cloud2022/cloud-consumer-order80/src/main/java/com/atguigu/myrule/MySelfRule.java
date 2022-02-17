package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@ClassName MySelfRule
 *@Description  TODO
 *@Author hqb
 *@Date 2022/2/14 12:38
 *@Version 1.0
 */
@Configuration
public class MySelfRule{

    @Bean
    public IRule myRule(){
        return new RandomRule();//定义为随机
    }
}

