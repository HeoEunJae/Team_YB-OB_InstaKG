package com.insta.project.answerComment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AnswerCommentForm {
    @NotEmpty(message = "내용은 필수")
    private String content;
}
