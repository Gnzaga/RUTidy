package com.AAACE.RUTidy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Bean
    public ExecutorService taskExecutor() {
        int numberOfThreads = 10; // You can customize the number of threads based on your requirements
        return Executors.newFixedThreadPool(numberOfThreads);
    }
}
