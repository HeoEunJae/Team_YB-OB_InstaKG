package com.insta.project.answerComment;

import com.insta.project.answer.domain.Answer;
import com.insta.project.answerComment.dao.AnswerCommentRepository;
import com.insta.project.answerComment.domain.AnswerComment;
import com.insta.project.question.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerCommentService {
    private final AnswerCommentRepository answerCommentRepository;
    public void create(Answer answer, Question question, String content){
        AnswerComment answerComment = new AnswerComment();
        answerComment.setContent(content);
        answerComment.setCreateDate(LocalDateTime.now());
        answerComment.setAnswer(answer);
        answerComment.setQuestion(question);
        answerComment.setReplyLike(false);
        this.answerCommentRepository.save(answerComment);
    }

    public void setLike(Integer answerCommentsId) {
        AnswerComment answerComment = answerCommentRepository.findById(answerCommentsId).get();
        if(answerComment.getReplyLike()==true) {
            answerComment.setReplyLike(false);
        } else {
            answerComment.setReplyLike(true);
        }
        this.answerCommentRepository.save(answerComment);
    }

}
