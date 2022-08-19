package com.insta.project.answer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AnswerForm {
    @NotEmpty(message = "내용은 필수")
    private String content;
}
