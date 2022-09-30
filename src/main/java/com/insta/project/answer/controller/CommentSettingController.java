package com.insta.project.answer.controller;

import com.insta.project.answer.AnswerForm;
import com.insta.project.answer.AnswerService;
import com.insta.project.answer.domain.Answer;
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
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentSettingController {
    private final AnswerService answerService;
    private final AuthService authService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Answer answer = this.answerService.getComment(id);
        this.answerService.delete(answer);
        return String.format("redirect:/question/list/detail/%s",answer.getQuestion().getId());
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, AnswerForm answerForm, @AuthenticationPrincipal UserDetails userDetails) {
        User user = authService.FindByEmail(userDetails.getUsername());
        Answer answer = this.answerService.getComment(id);
        answerForm.setContent(answerForm.getContent());
        model.addAttribute("answer", answer);
        model.addAttribute("user", user);
        return "CommentModify";
    }

    @PostMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, @RequestParam(value = "onOff", required = false) Boolean onOff, @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Answer answer = this.answerService.getComment(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("answer", answer);
            return "CommentModify";
        }
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/question/list/detail/%s",answer.getQuestion().getId());
    }

    @PostMapping("/modify/{id}/img")
    public String modifyImg(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetails userDetails, @RequestParam("profileImageUrl") List<MultipartFile> profileImage) throws Exception {
        User user = authService.FindByEmail(userDetails.getUsername());
        Answer answer = this.answerService.getComment(id);
        authService.ChangeProfileImage(profileImage, user.getEmail());
        return String.format("redirect:/question/list/detail/%s",answer.getQuestion().getId());
    }
}

