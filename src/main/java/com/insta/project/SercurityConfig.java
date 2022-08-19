package com.insta.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SercurityConfig {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable(); //인증절차 패스
//        http.authorizeRequests()
//                .antMatchers("/", "/user/**", "/image/**", "/follow/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") //막을것만 막고 나머진 허용 / authenticated:로그인만하면 허용 /
//                .antMatchers("/admin/**").access("hasRole('Role_ADMIN')")
//                .anyRequest()
//                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/auth/signin")
//                .loginProcessingUrl("/auth/signin") // post/login 주소를 디스패처가 확인하면 필터가 낚아챔
//                .defaultSuccessUrl("/");

        return http.build();
    }
}
