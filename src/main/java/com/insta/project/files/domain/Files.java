package com.insta.project.files.domain;

import com.insta.project.question.domain.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity

public class Files {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;

@ManyToOne
    private Question question;
}
