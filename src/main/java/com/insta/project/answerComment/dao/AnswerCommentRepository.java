package com.insta.project.answerComment.dao;

import com.insta.project.answerComment.domain.AnswerComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Integer> {
}
