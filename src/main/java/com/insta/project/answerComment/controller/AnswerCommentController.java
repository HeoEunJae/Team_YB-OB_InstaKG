package com.insta.project.answerComment.controller;

import com.insta.project.answer.AnswerService;
import com.insta.project.answer.domain.Answer;
import com.insta.project.answerComment.AnswerCommentForm;
import com.insta.project.answerComment.AnswerCommentService;
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
public class AnswerCommentController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    private final AnswerCommentService answerCommentService;

    @PostMapping("/create/detail/{questionId}/{answerId}")
    public String createAnswer(Model model, @PathVariable("questionId") Integer questionId, @PathVariable("answerId") Integer answerId, @Valid AnswerCommentForm answerCommentForm, BindingResult bindingResult){
        Answer answer = this.answerService.getComment(answerId);
        Question question = this.questionService.getQuestion(questionId);
        if(bindingResult.hasErrors()){
            model.addAttribute("answer", answer);
            return "question_detail";
        }
        this.answerCommentService.create(answer,question, answerCommentForm.getContent());
        return String.format("redirect:/question/list/detail/%s", questionId);
    }

    @PostMapping("/comment/detail/like/{questionId}/{answerId}/{answerCommentsId}")
    public String createCommentsAnswer(@PathVariable("questionId") Integer questionId, @PathVariable("answerId") Integer answerId, @PathVariable("answerCommentsId") Integer answerCommentsId) {
        this.answerCommentService.setLike(answerCommentsId);

        return String.format("redirect:/question/list/detail/%s", questionId);
    }

}

