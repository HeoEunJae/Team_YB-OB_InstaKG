package com.insta.project.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByUsername(String username);
    User findByEmail(String email);
}
