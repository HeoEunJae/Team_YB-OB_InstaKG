package com.insta.project.answer.controller;

import com.insta.project.answer.AnswerForm;
import com.insta.project.answer.AnswerService;
import com.insta.project.answer.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentSettingController {
    private final AnswerService answerService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Answer answer = this.answerService.getComment(id);
        this.answerService.delete(answer);
        return String.format("redirect:/instakg/list/detail/%s",answer.getQuestion().getId());
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Answer answer = this.answerService.getComment(id);
        answerForm.setContent(answerForm.getContent());
        model.addAttribute("answer", answer);
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


}

