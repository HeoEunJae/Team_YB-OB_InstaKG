package com.insta.project;

import com.insta.project.question.domain.Question;
import com.insta.project.question.service.QuestionService;
import com.insta.project.user.AuthService;
import com.insta.project.user.ModifyDTO;
import com.insta.project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class MainController {

    private final AuthService authService;
    private final QuestionService questionService;

    @GetMapping("/list")
    public String story(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = authService.FindByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        List<Question> questionList = this.questionService.getList(user.getEmail());
        Collections.sort(questionList, (a, b) -> b.getId() - a.getId());
        model.addAttribute("question", questionList);
        return "story";
    }

    @GetMapping("/profile")
//    @ResponseBody
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) throws Exception {
        User user = authService.FindByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        List<Question> questionList = this.questionService.getList(user.getEmail());
        Collections.sort(questionList, (a, b) -> b.getId() - a.getId());
        model.addAttribute("question", questionList);
        return "profile";
    }

    @GetMapping("/setprofile")
    public String setprofile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = authService.FindByEmail(userDetails.getUsername());
        System.out.println(user);
        model.addAttribute("user",user);
        return "setprofile.html";
    }

    @PostMapping("/setprofile")
    public String Update(@AuthenticationPrincipal UserDetails userDetails, ModifyDTO modifyDTO) throws Exception {
        User user = authService.FindByEmail(userDetails.getUsername());
        authService.modify(modifyDTO, userDetails);
        return "redirect:/question/setprofile";
    }

    @PostMapping("/setprofileImg")
    public String imgUpdate(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("profileImageUrl") List<MultipartFile> profileImage) throws Exception {
        User user = authService.FindByEmail(userDetails.getUsername());
        authService.ChangeProfileImage(profileImage, user.getEmail());
        return "redirect:/question/setprofile";
    }

    @GetMapping("/")
    public String question() {
        return "redirect:question/list";
    }

//    public void models(@AuthenticationPrincipal UserDetails userDetails, Model model){
//        User user = authService.FindByEmail(userDetails.getUsername());
//        System.out.println(user);
//        model.addAttribute("userinfo", user);
//        model.addAttribute("name", user.getName());
//        model.addAttribute("username", user.getUsername());
//        model.addAttribute("bio", user.getBio());
//        model.addAttribute("email", userDetails.getUsername());
//        model.addAttribute("phone", user.getPhone());
//        model.addAttribute("gender", user.getGender());
//        model.addAttribute("profileImageUrl", user.getProfileImageUrl());
//        model.addAttribute("ProfileImagePath", user.getProfileImagePath());
//
//    }
}


