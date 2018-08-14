package com.sinohealth.dscp;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableEurekaClient //Eureka的注解
@EnableDiscoveryClient  //为其他发现组件提供支持
@EnableHystrix
@EnableHystrixDashboard
@MapperScan("com.sinohealth.dscp.mapper")
public class DrugApplication {

    private static final Logger logger = LoggerFactory.getLogger(DrugApplication.class);

    public static void main(String[] args) {
        logger.info("Drug Service Running...");
        SpringApplication.run(DrugApplication.class, args);
    }

}
