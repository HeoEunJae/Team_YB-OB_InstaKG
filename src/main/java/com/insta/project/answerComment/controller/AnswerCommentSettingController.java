package com.insta.project.answerComment.controller;

import com.insta.project.answer.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/co")
@RequiredArgsConstructor
public class AnswerCommentSettingController {
    private final AnswerService answerService;

   /* @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Answer answer = this.answerService.getComment(id);
        this.answerService.delete(answer);
        return String.format("redirect:/question/list/detail/%s",answer.getQuestion().getId());
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
        this.answerService.modify(answer, answerForm.getContent(), onOff);
        return String.format("redirect:/question/list/detail/%s",answer.getQuestion().getId());
    }
*/

}

