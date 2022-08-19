package com.insta.project.answer.controller;

import com.insta.project.answer.AnswerForm;
import com.insta.project.answer.AnswerService;
import com.insta.project.question.domain.Question;
import com.insta.project.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult){
        Question question = this.questionService.getQuestion(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question, answerForm.getContent());
        return String.format("redirect:/question/list/detail/%s", id);
    }

    @PostMapping("/detail/like/{questionId}/{answerId}")
    public String createAnswer(@PathVariable("questionId") Integer questionId, @PathVariable("answerId") Integer answerId) {
        this.answerService.setLike(answerId);
        return String.format("redirect:/question/list/detail/%s", questionId);
    }

    @PostMapping("/like/{questionId}/{answerId}")
    public String createAnswers(@PathVariable("questionId") Integer questionId, @PathVariable("answerId") Integer answerId) {
        this.answerService.setLike(answerId);
        return "redirect:/question/list";
    }

}
