package com.insta.project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service // 1. IoC  2. 트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional // Write(Insert, Update, Delete)
    public User 회원가입(User user) throws RuntimeException{
        // 회원가입 진행
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER"); // 관리자 ROLE_ADMIN
        User userEntity = null;
        userEntity = userRepository.save(user);
        return userEntity;
    }

    @Transactional
    public void modify(ModifyDTO modifyDTO, @AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByEmail(userDetails.getUsername());
        user.modify(
                modifyDTO.getMDusername(),
                modifyDTO.getMDname(),
                modifyDTO.getMDbio(),
                modifyDTO.getMDphone(),
                modifyDTO.getMDprofileImageUrl()
        );
    }

    public User FindByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }

    public boolean existEmail(String email) {
        if(userRepository.existsByEmail(email)){
            return true;
        }
        return false;
    }
}