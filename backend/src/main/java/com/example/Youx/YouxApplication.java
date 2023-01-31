package com.example.Youx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication //(exclude = {SecurityAutoConfiguration.class})
public class YouxApplication {


    public static void main(String[] args) {
        SpringApplication.run(YouxApplication.class, args);
    }

}
