package com.insta.project.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String name;

    private String password;

    @Column(unique = true)
    private String email;

    private String bio;

    private String phone;

    private String gender;

    private String profileImageUrl;
    private String profileImagePath;

    private String role;

    @CreationTimestamp
    private Timestamp createDate;

    public void modify(String username, String name, String bio, String phone, String gender) {
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.phone = phone;
        this.gender = gender;
//        this.profileImageUrl = profileImageUrl;
    }
}
