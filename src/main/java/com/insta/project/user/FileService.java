package com.insta.project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final UserRepository userRepository;

    public void UploadProfileImage(List<MultipartFile> profileImage, User user) throws Exception{
        System.out.println(profileImage+"1111111111111");

        try {
            for (MultipartFile file : profileImage) {
                String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\userProfile";

                UUID uuid = UUID.randomUUID();

                String fileName = uuid + "_" + file.getOriginalFilename();

                File saveFile = new File(projectPath, fileName);

                file.transferTo(saveFile);
                deleteFile(user.getProfileImageUrl());
                user.setProfileImagePath("/userProfile/" + fileName);

                user.setProfileImageUrl(fileName);

                userRepository.save(user);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteFile(String imgUrl) {
        String root = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\userProfile\\" + imgUrl;
        new File(root).delete();
    }
}
