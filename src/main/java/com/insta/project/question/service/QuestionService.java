package com.insta.project.question.service;

import com.amazonaws.services.s3.AmazonS3;
import com.insta.project.DataNotFoundException;
import com.insta.project.files.domain.Files;
import com.insta.project.question.dao.QuestionRepository;
import com.insta.project.question.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;


    public List<Question> getList(String email) {
        return this.questionRepository.findByEmail(email);
    }

/*
    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 5, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }
*/

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public Question create(String content, String email){
        Question question = new Question();
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setModifyDate(LocalDateTime.now());
        question.setReplyLike(0);
        question.setEmail(email);
        this.questionRepository.save(question);
        return question;
    }

    public Integer setLike(Integer questionId) {
        Question question = questionRepository.findById(questionId).get();

        if(question.getReplyLike() == 1){
            question.setReplyLike(0);
        }else {
            question.setReplyLike(1);
        }
        this.questionRepository.save(question);
        return question.getReplyLike();
    }


    public void delete(Question question){
        for(Files file: question.getFileList()){
            amazonS3.deleteObject(bucket, file.getFilename());
        }
        this.questionRepository.delete(question);
    }

    public void modify(Question question, String content, Boolean onOff){
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        question.setOnOff(onOff);
        this.questionRepository.save(question);
    }
}
