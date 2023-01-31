package com.example.Youx.data.vo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
// @EnableGlobalMethodSecurity vai ser descontinuada em breve , então mais viavel requestMatchers
public class WebSecurityConfig implements WebMvcConfigurer {


    //websecurityconfigureradapter foi descontinuada nas versão recentes novo metodo de autenticação

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic().and().authorizeHttpRequests()
                //usar hasAuthority e hasAnyAuthority , pois hasRole está descontinuada junto com antMatchers
                //requestMatchers substitui o antMatchers
                .requestMatchers(HttpMethod.GET, "/pacientes").hasAnyAuthority("PERMISSAO_ADM", "PERMISSAO_USER")
                .requestMatchers(HttpMethod.GET, "/pacientes/**").hasAnyAuthority("PERMISSAO_ADM", "PERMISSAO_USER")
                .requestMatchers(HttpMethod.POST, "/pacientes").hasAuthority("PERMISSAO_ADM")
                .and().csrf().disable();

        return http.build();
    }

    //bloco necessario para evitar erro de Cors  necessario implements WebMvcConfigurer
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
