package com.insta.project.question.controller;

import com.insta.project.answer.AnswerForm;
import com.insta.project.answer.AnswerService;
import com.insta.project.question.QuestionForm;
import com.insta.project.question.domain.Question;
import com.insta.project.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/question")

public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;



    @RequestMapping("/list")
    public String showQuestions(Model model) {
        List<Question> questionList = this.questionService.getList();
        Collections.sort(questionList, (a, b) -> b.getId() - a.getId());
        model.addAttribute("question", questionList);
        return "story";
    }

    @RequestMapping("/user")
    public String UserDetail(Model model) {
        return "profile";
    }

    @RequestMapping("list/detail/{id}")
    public String showDetail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        Collections.sort(question.getAnswerList(), (a, b) -> b.getId() - a.getId());
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }

/*    @PostMapping("/create")
    public String questionPageCreate(Question question){
        questionService.create(question);
        return "redirect:/question/list";
    }*/

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult){
        Question question = this.questionService.getQuestion(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "story";
        }
        this.answerService.create(question, answerForm.getContent());
        return "redirect:/question/list";
    }

    @RequestMapping("/like/{id}")
    public String questionLike(@PathVariable("id") Integer id){
        this.questionService.setLike(id);
        return "redirect:/question/list";
    }

    @RequestMapping("/like")
    public String questionlike(@RequestParam("id") Integer id){
        this.questionService.setLike(id);
        return "redirect:/question/list";
    }


    @PostMapping("/detail/like/{id}")
    public String questionDetailLike(@PathVariable("id") Integer id){
        this.questionService.setLike(id);
        return String.format("redirect:/question/list/detail/%s",id);
    }

    @GetMapping("/profile")
//    @ResponseBody
    public String profile(Model model){
        List<Question> questionList = this.questionService.getList();
        Collections.sort(questionList, (a, b) -> b.getId() - a.getId());
        model.addAttribute("question", questionList);
        return "profile";
    }
}
