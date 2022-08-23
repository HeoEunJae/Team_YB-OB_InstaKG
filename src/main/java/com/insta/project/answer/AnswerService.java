package com.insta.project.answer;

import com.insta.project.DataNotFoundException;
import com.insta.project.answer.dao.AnswerRepository;
import com.insta.project.answer.domain.Answer;
import com.insta.project.question.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    public Answer getComment(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    public void create(Question question, String content){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setReplyLike(0);
        this.answerRepository.save(answer);
    }

    public Integer setLike(Integer answerId) {
        Answer answer = answerRepository.findById(answerId).get();
        if(answer.getReplyLike() == 1) {
            answer.setReplyLike(0);
        } else {
            answer.setReplyLike(1);
        }
        this.answerRepository.save(answer);
        return answer.getReplyLike();
    }

    public void delete(Answer answer){
        this.answerRepository.delete(answer);
    }

    public void modify(Answer answer, String content){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
}
