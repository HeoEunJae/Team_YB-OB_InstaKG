package com.insta.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests(authroize -> authroize.antMatchers("/user/**")
                        .authenticated()
                        .antMatchers("/manager/**")
                        .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                        .antMatchers("/admin/**")
                        .access("hasRole('ROLE_ADMIN')")
                        .anyRequest().permitAll())
                //인증 안된 페이지로 가면 로그인 페이지로 보냄
                .formLogin()
                .loginPage("/login")
//                .failureHandler(loginFailHandler())
//                .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 로그인을 진행함
                .defaultSuccessUrl("/story")
                .and()
                .build();
    }
}
