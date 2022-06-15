package com.isaiah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: IsaiahLu
 * @date: 2022/6/13 0:27
 */


/*@EnableFeignClients    //服务调用
@EnableDiscoveryClient  //nacos注册*/
//@ComponentScan(basePackages = {"com.a"})

@ComponentScan(basePackages ={"com.isaiah"} )
@SpringBootApplication
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
