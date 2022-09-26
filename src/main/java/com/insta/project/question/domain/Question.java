package com.insta.project.question.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.insta.project.answer.domain.Answer;
import com.insta.project.answerComment.domain.AnswerComment;
import com.insta.project.files.domain.Files;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Question {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(columnDefinition = "integer default 0", nullable = false)

    private Integer replyLike;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private Integer onOff;

    private String email;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Files> fileList;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<AnswerComment> answerCommentsList;

//    @Converter
//    class BooleanToYNConverter implements AttributeConverter<Boolean, String> {
//        @Override
//        public String convertToDatabaseColumn(Boolean attribute) {
//            return (attribute != null && attribute) ? "Y" : "N";
//        }
//
//        @Override
//        public Boolean convertToEntityAttribute(String dbData) {
//            return "Y".equals(dbData);
//        }
//    }
}