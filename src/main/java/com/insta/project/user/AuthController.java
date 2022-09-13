package com.insta.project.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor // final 필드를 DI 할때 사용
@Controller // 1. IoC  2. 파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/story")
    public String story() {
        return "story";
    }

    // 회원가입버튼 -> /auth/signup -> /auth/signin
    // 회원가입버튼 X
    @PostMapping("/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult, Model model) { // key=value (x-www-form-urlencoded)
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (authService.existEmail(signupDto.getEmail())) {
            model.addAttribute("error", 1);
            return "signup";
        }

        // User < - SignupDto
        User user = signupDto.toEntity();
        User userEntity = authService.회원가입(user);
        System.out.println(userEntity);
        return "login";
    }

    public String index(@AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails!=null) {
            System.out.println(userDetails.getUsername());
        }
        return "index";
    }
}
