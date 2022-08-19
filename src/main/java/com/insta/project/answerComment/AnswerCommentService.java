package com.insta.project.answerComment;

import com.insta.project.DataNotFoundException;
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
    private final AnswerCommentRepository answerRepository;
    public AnswerComment getComment(Integer id) {
        Optional<AnswerComment> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    public void create(Question question, String content){
        AnswerComment answer = new AnswerComment();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setReplyLike(false);
        this.answerRepository.save(answer);
    }

    public void setLike(Integer answerId) {
        AnswerComment answer = answerRepository.findById(answerId).get();
        if(answer.getReplyLike() == true) {
            answer.setReplyLike(false);
        } else {
            answer.setReplyLike(true);
        }
        this.answerRepository.save(answer);
    }

    public void delete(AnswerComment answer){
        this.answerRepository.delete(answer);
    }

    public void modify(AnswerComment answer, String content, Boolean onOff){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
}
