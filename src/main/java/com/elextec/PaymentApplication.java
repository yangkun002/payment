package com.elextec;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.elextec.persist.dao.mybatis"})
@EnableTransactionManagement
@ServletComponentScan
@EnableJms
public class PaymentApplication {
        public static void main(String[] args) {SpringApplication.run(PaymentApplication.class, args);}
}
