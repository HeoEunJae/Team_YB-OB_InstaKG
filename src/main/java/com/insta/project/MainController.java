package com.insta.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MainController {

//    private final UserRepository userRepository;


    //    @PostMapping("/signup")
//    public String signForm(User user) {
//        System.out.println("user");
//        System.out.println(user);
//        user.setRole("ROLE_USER");
//        String rawPassword = user.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        user.setPassword(encPassword);
//        userRepository.save(user);
//        return "redirect:/login";
//    }
//    @GetMapping("/signup")
////    @ResponseBody
//    public String signup() {
//        return "signup";
//    }
//    @GetMapping("/login")
////    @ResponseBody
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/signup")
////    @ResponseBody
//    public String signup() {
//        return "signup";
//    }

    @GetMapping("/story")
//    @ResponseBody
    public String story() {
        return "story";
    }

    @GetMapping("/profile")
//    @ResponseBody
    public String profile() {
        return "profile";
    }

//    @GetMapping("/setprofile")
////    @ResponseBody
//    public String setprofile() {
//        return "setprofile";
//    }

    @GetMapping("/")
//    @ResponseBody
    public String question() {
        return "redirect:question/list";
    }
}


