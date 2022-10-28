package com.insta.project.user;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public void UploadProfileImage(List<MultipartFile> profileImage, User user) throws Exception {
        profileImage.stream()
                .forEach(file -> {
                    String currentImg = user.getProfileImagePath();
                    boolean isExistObject = amazonS3.doesObjectExist(bucket, currentImg);
                    if (isExistObject == true) {
                        amazonS3.deleteObject(bucket, currentImg);
                        deleteFile(user);
                    }

                    String originFile = file.getOriginalFilename();
                    String ext = originFile.substring(originFile.lastIndexOf("."));
                    String s3FileName = UUID.randomUUID().toString() + ext;
                    user.setProfileImagePath(s3FileName);
                    userRepository.save(user);

                    ObjectMetadata objMeta = new ObjectMetadata();

                    try {
                        objMeta.setContentLength(file.getInputStream().available());
                        amazonS3.putObject(bucket, s3FileName, file.getInputStream(), objMeta);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });

    }

    public void deleteFile(User user) {
        user.setProfileImagePath("");
        userRepository.save(user);
    }
}
