package com.insta.project.question.controller;

import com.insta.project.question.QuestionForm;
import com.insta.project.question.domain.Question;
import com.insta.project.question.service.QuestionService;
import com.insta.project.user.AuthService;
import com.insta.project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingController {
    private final QuestionService questionService;
    private final AuthService authService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Question question = this.questionService.getQuestion(id);
        this.questionService.delete(question);
        return "redirect:/question/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, QuestionForm questionForm, @AuthenticationPrincipal UserDetails userDetails) {
        User user = authService.FindByEmail(userDetails.getUsername());
        Question question = this.questionService.getQuestion(id);
        questionForm.setContent(questionForm.getContent());
        model.addAttribute("question", question);
        model.addAttribute("user",user);
        return "modify";
    }

    @PostMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, @RequestParam(required = false) Boolean onOff, @Valid QuestionForm questionForm, BindingResult bindingResult) {
        Question question = this.questionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "modify";
        }
        this.questionService.modify(question, questionForm.getContent(), onOff);
        return "redirect:/question/list";
    }

    @PostMapping("/modify/{id}/img")
    public String modifyImg(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("profileImageUrl") List<MultipartFile> profileImage) throws Exception {
        User user = authService.FindByEmail(userDetails.getUsername());
        authService.ChangeProfileImage(profileImage, user.getEmail());
        return "redirect:/question/list";
    }


}

