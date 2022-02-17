package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.management.RuntimeErrorException;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName PaymentService
 *@Description  TODO
 *@Author hqb
 *@Date 2022/2/14 16:23
 *@Version 1.0
 */

@Service
public class PaymentService{


    /**
     * 服务Fallback
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){

        return "线程池" + Thread.currentThread().getName()+ "paymentInfo_ok" + id +"O(∩_∩)O";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_Timeout(Integer id){

//        int timeNumber = 5;
//        int age = 10/0;
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + "id;" + id +"O(∩_∩)O" + "耗时（s）:";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池 " + Thread.currentThread().getName() +"8001系统繁忙或运行报错，请稍后再试,id：" + id + "\t" + "/(ㄒoㄒ)/~~";
    }

    /**
     *上图故意制造两个异常
     * 1.int age = 10/0
     * 2.我们接收三秒钟异常，它运行五秒，超时异常
     * 当前服务不可用，做服务降级，兜底的方案都是paymentInfo_TimeOutHandler
     */


    //=======服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸

    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试，/(ㄒoㄒ)/~~ id:" + id;
    }
}

