package com.insta.project.files.Controller;

import com.insta.project.files.service.FilesService;
import com.insta.project.question.QuestionForm;
import com.insta.project.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilesController {
    private final FilesService filesService;
    private final QuestionService questionService;

    @GetMapping("/multi-file")
    public String showMultiForm(){
        return "/question";
    }

    @PostMapping("/multi-file")
    public String multiFileUpload(
            Model model,
            @RequestParam("multiFile") List<MultipartFile> multiFileList,
            QuestionForm questionForm,
            @AuthenticationPrincipal UserDetails userDetails
    )throws InterruptedException{

        try{
            filesService.upload(questionForm, multiFileList, userDetails.getUsername());

        }catch(Exception e){
            questionService.create(questionForm.getContent(), userDetails.getUsername());
        }
        return "redirect:/question/list";
    }
}