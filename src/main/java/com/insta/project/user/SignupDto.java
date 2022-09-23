package com.insta.project.user;

import lombok.Data;

@Data // Getter, Setter
public class SignupDto {

    private String username;
    private String password;
    private String email;
    private String name;

    private String profileImageUrl;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}