package com.insta.project.files.dao;

import com.insta.project.files.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FilesRepository extends JpaRepository<Files, Long> {
}
