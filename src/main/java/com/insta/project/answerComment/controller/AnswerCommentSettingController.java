package com.insta.project.answerComment.controller;

import com.insta.project.answer.AnswerForm;
import com.insta.project.answerComment.AnswerCommentForm;
import com.insta.project.answerComment.AnswerCommentService;
import com.insta.project.answerComment.domain.AnswerComment;
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
@RequestMapping("/co")
@RequiredArgsConstructor
public class AnswerCommentSettingController {
    private final AnswerCommentService answerCommentService;
    private final AuthService authService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        AnswerComment answerComment = this.answerCommentService.getAnswerComment(id);
        this.answerCommentService.delete(answerComment);
        return String.format("redirect:/question/list/detail/%s",answerComment.getQuestion().getId());
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, AnswerForm answerForm, @AuthenticationPrincipal UserDetails userDetails) {
        User user = authService.FindByEmail(userDetails.getUsername());
        AnswerComment answerComment = this.answerCommentService.getAnswerComment(id);
        answerForm.setContent(answerForm.getContent());
        model.addAttribute("answer", answerComment);
        model.addAttribute("user", user);
        return "AnswerCommentModify";
    }

    @PostMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, @RequestParam(value = "onOff", required = false) Boolean onOff, @Valid AnswerCommentForm answerCommentForm, BindingResult bindingResult) {
        AnswerComment answerComment = this.answerCommentService.getAnswerComment(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("answer", answerComment);
            return "AnswerCommentModify";
        }
        this.answerCommentService.modify(answerComment, answerCommentForm.getContent());
        return String.format("redirect:/question/list/detail/%s",answerComment.getQuestion().getId());
    }

    @PostMapping("/modify/{id}/img")
    public String modifyImg(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetails userDetails, @RequestParam("profileImageUrl") List<MultipartFile> profileImage) throws Exception {
        User user = authService.FindByEmail(userDetails.getUsername());
        AnswerComment answerComment = this.answerCommentService.getAnswerComment(id);
        authService.ChangeProfileImage(profileImage, user.getEmail());
        return String.format("redirect:/question/list/detail/%s",answerComment.getQuestion().getId());
    }

}

