package com.insta.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/login")
//    @ResponseBody
    public String loginForm(){
        return "login";
    }

    @GetMapping("/signup")
//    @ResponseBody
    public String signForm(){
        return "signup";
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


