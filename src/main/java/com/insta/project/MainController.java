package com.insta.project;

import com.insta.project.question.domain.Question;
import com.insta.project.question.service.QuestionService;
import com.insta.project.user.AuthService;
import com.insta.project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class MainController {

//    private final UserRepository userRepository;
    private final AuthService authService;
    private final QuestionService questionService;


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

    @GetMapping("/setprofile")
    public String setprofile(@AuthenticationPrincipal UserDetails userDetails, Model model, User user){
        user = authService.FindByEmail(userDetails.getUsername());
        model.addAttribute("userinfo", user);
        System.out.println("1111111111111111111"+userDetails.getUsername());
        model.addAttribute("name", user.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", userDetails.getUsername());
        return "setprofile";
    }

    @GetMapping("/profile")
//    @ResponseBody
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model, User user) {
        user = authService.FindByEmail(userDetails.getUsername());
        model.addAttribute("userinfo", user);
        model.addAttribute("name", user.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", userDetails.getUsername());
        List<Question> questionList = this.questionService.getList();
        Collections.sort(questionList, (a, b) -> b.getId() - a.getId());
        model.addAttribute("question", questionList);
        return "profile";
    }

    @GetMapping("/")
//    @ResponseBody
    public String question() {
        return "redirect:question/list";
    }
}


