package com.insta.project.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

            String errorMessage = exception.getMessage();

            if (exception instanceof BadCredentialsException) {
                errorMessage = "잘못된 아이디 혹은 패스워드입니다!";
            } else if (exception instanceof InsufficientAuthenticationException) {
                errorMessage = "암호키가 잘못되었습니다!";
            } else if (exception instanceof UsernameNotFoundException) {
                errorMessage = "존재하지 않는 아이디 입니다.";
            }

            // UTF-8 인코딩 처리
            errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
            setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);

            super.onAuthenticationFailure(request, response, exception);

        }
    }