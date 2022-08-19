package com.insta.project.question;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message = "내용 입력 必")
    private String content;
}
