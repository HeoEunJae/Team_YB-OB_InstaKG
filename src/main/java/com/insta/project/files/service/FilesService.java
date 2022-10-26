package com.insta.project.files.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.insta.project.files.dao.FilesRepository;
import com.insta.project.files.domain.Files;
import com.insta.project.question.QuestionForm;
import com.insta.project.question.domain.Question;
import com.insta.project.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilesService {
    private final FilesRepository filesRepository;
    private final QuestionService questionService;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

//    @Async
//    public void upload(QuestionForm questionForm, List<MultipartFile> multiFileList, String email){
//
//        String root = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploadFiles";
//
//        File fileCheck = new File(root);
//        if (!fileCheck.exists()) fileCheck.mkdirs();
//
//        List<Map<String, String>> fileList = new ArrayList<>();
//
//        for(int i = 0; i < multiFileList.size(); i++){
//            String originFile = multiFileList.get(i).getOriginalFilename();
//            String ext = originFile.substring(originFile.lastIndexOf("."));
//            String changeFile = UUID.randomUUID().toString() + ext;
//
//            Map<String, String> map = new HashMap<>();
//            map.put("originFile", originFile);
//            map.put("changeFile", changeFile);
//
//            fileList.add(map);
//        }
//
//        try{
//            for(int i = 0; i < multiFileList.size(); i++){
//                File uploadFile = new File(root + "\\" + fileList.get(i).get("changeFile"));
//                multiFileList.get(i).transferTo(uploadFile);
//            }
//            uploadDB(fileList, questionForm, email);
//            System.out.println("다중 파일 업로드 성공!");
//        }catch(IllegalStateException | IOException e){
//            System.out.println("다중 파일 업로드 실패 ㅠㅠ");
//            for(int i = 0; i < multiFileList.size(); i++){
//                new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
//            }
//            e.printStackTrace();
//        }
//    }

    private void uploadDB(String fileName, QuestionForm questionForm, String email, Question question) {
            Files Files = new Files();
            Files.setFilename(fileName);
            Files.setQuestion(question);
            filesRepository.save(Files);
    }

    public void awsUploadTest(QuestionForm questionForm, List<MultipartFile> files, String email) throws IOException {
        Question question = questionService.create(questionForm.getContent(), email);
        files.stream()
                .forEach(file->{
                    String originFile = file.getOriginalFilename();
                    String ext = originFile.substring(originFile.lastIndexOf("."));
                    String s3FileName = UUID.randomUUID().toString() + ext;
                    uploadDB(s3FileName,questionForm,email,question);
                    ObjectMetadata objMeta = new ObjectMetadata();
                    try{
                        objMeta.setContentLength(file.getInputStream().available());
                        amazonS3.putObject(bucket, s3FileName, file.getInputStream(), objMeta);
                    }catch(IOException e){
                        throw new RuntimeException(e);
                    }
                });

    }
}