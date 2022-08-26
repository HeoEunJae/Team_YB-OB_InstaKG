package com.insta.project;

import com.insta.project.user.User;
import com.insta.project.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/signup")
//    @ResponseBody
    public String signForm(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signForm(User user) {
        System.out.println("user");
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/story")
//    @ResponseBody
    public String story(){
        return "story";
    }

    @GetMapping("/profile")
//    @ResponseBody
    public String profile(){
        return "profile";
    }

    @GetMapping("/setprofile")
//    @ResponseBody
    public String setprofile(){
        return "setprofile";
    }

    @GetMapping("/")
//    @ResponseBody
    public String question(){
        return "redirect:question/list";
    }
}


