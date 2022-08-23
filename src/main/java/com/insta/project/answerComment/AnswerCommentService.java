package com.insta.project.answerComment;

import com.insta.project.DataNotFoundException;
import com.insta.project.answer.domain.Answer;
import com.insta.project.answerComment.dao.AnswerCommentRepository;
import com.insta.project.answerComment.domain.AnswerComment;
import com.insta.project.question.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
        answerComment.setReplyLike(0);
        this.answerCommentRepository.save(answerComment);
    }

    public Integer setLike(Integer answerCommentsId) {
        AnswerComment answerComment = answerCommentRepository.findById(answerCommentsId).get();
        if(answerComment.getReplyLike()==1) {
            answerComment.setReplyLike(0);
        } else {
            answerComment.setReplyLike(1);
        }
        this.answerCommentRepository.save(answerComment);
        return answerComment.getReplyLike();
    }

    public AnswerComment getAnswerComment(Integer id){
        Optional<AnswerComment> answerComment = this.answerCommentRepository.findById(id);
        if (answerComment.isPresent()) {
            return answerComment.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void delete(AnswerComment answerComment) {
        this.answerCommentRepository.delete(answerComment);
    }

    public void modify(AnswerComment answerComment, String content) {
        answerComment.setContent(content);
        answerComment.setModifyDate(LocalDateTime.now());
        this.answerCommentRepository.save(answerComment);
    }
}
