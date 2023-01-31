package com.example.Youx.data.vo.security;

import org.springframework.context.annotation.Bean;

import java.util.Base64;

public class CriptografiaCPF {

    @Bean
    public String criptografar(String cpf) {
        return new String(Base64.getEncoder().encode(cpf.getBytes()));
    }

    @Bean
    public  String descriptografar(String cpf) {
        return new String(Base64.getDecoder().decode(cpf.getBytes()));
    }
}
