package com.insta.project.question.service;

import com.insta.project.DataNotFoundException;
import com.insta.project.files.domain.Files;
import com.insta.project.question.dao.QuestionRepository;
import com.insta.project.question.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

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
 /*
  // 이미지 다중 이미지 구현 2
    public void create(Question question, List<MultipartFile> files) throws Exception {
        String fileName = null;
        File saveFile;
        try {
            String totalFile = "";
            String totalFilePath = "";
            for(MultipartFile file : files){
                UUID uuid = UUID.randomUUID();
                fileName = uuid + "_" + file.getOriginalFilename();
                totalFile += fileName + "*";
                totalFilePath += "/files/" + fileName + "*";
            }

            for (MultipartFile file : files) {
                String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

               UUID uuid = UUID.randomUUID();

                fileName = uuid + "_" + file.getOriginalFilename();*//*

                saveFile = new File(projectPath, fileName);

                file.transferTo(saveFile);

                question.setFilepath(totalFilePath);

                question.setFilename(totalFile);

                question.setCreateDate(LocalDateTime.now());

                question.setReplyLike(false);

                questionRepository.save(question);

            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }
*/


/*
    public void updateHit(Integer id) {
        Question question = getQuestion(id);
        int count = question.getHit();
        question.setHit(count + 1);
        questionRepository.save(question);
    }
*/

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
        String root = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploadFiles";
        for(Files file: question.getFileList()){
            new File(root + "\\" + file.getFilename()).delete();
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
