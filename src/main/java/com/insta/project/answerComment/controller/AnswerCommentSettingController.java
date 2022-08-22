package com.insta.project.answerComment.controller;

import com.insta.project.answer.AnswerForm;
import com.insta.project.answerComment.AnswerCommentForm;
import com.insta.project.answerComment.AnswerCommentService;
import com.insta.project.answerComment.domain.AnswerComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/co")
@RequiredArgsConstructor
public class AnswerCommentSettingController {
    private final AnswerCommentService answerCommentService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        AnswerComment answerComment = this.answerCommentService.getAnswerComment(id);
        this.answerCommentService.delete(answerComment);
        return String.format("redirect:/question/list/detail/%s",answerComment.getQuestion().getId());
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        AnswerComment answerComment = this.answerCommentService.getAnswerComment(id);
        answerForm.setContent(answerForm.getContent());
        model.addAttribute("answer", answerComment);
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

}

