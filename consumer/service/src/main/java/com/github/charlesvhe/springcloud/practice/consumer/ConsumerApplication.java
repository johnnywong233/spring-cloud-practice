package com.github.charlesvhe.springcloud.practice.consumer;

import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.repository.FileSystemTransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by charles on 2017/5/22.
 */
@EnableSwagger2
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.github.charlesvhe.springcloud.practice.provider"})
@ImportResource("classpath:tcc-transaction.xml")
public class ConsumerApplication {
    @Bean
    public Docket docket(@Value("${swagger.enable:true}") boolean enable) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(this.getClass().getPackage().getName()))
                .build()
                .enable(enable);
    }

    @Bean
    public TransactionRepository transactionRepository(){
        FileSystemTransactionRepository transactionRepository = new FileSystemTransactionRepository();
        transactionRepository.setRootPath("/Users/charles/work/IdeaProjects/spring-cloud-practice/consumer/tcc");
        return transactionRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}