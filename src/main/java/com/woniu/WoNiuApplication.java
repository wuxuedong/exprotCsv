package com.woniu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class WoNiuApplication {

    public static void main(String[] args) {
        SpringApplication.run(WoNiuApplication.class, args);
        log.info("test_info");
        log.error("test_error");
        log.warn("test_warn");
    }



}
