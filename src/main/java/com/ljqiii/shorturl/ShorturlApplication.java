package com.ljqiii.shorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
public class ShorturlApplication {
    public static ConfigurableApplicationContext context;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    public static void main(String[] args) {
        context = SpringApplication.run(ShorturlApplication.class, args);
    }

}
