package com.test.preproject040.domain.Hashtag.Controller;


import com.test.preproject040.domain.Hashtag.DTO.QuestionSaveRequestDTO;
import com.test.preproject040.domain.Hashtag.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    public String getQuestions(Model model){
        model.addAttribute("questions", questionService.findAll());
        return "questions/index";
    }

    @GetMapping(value = " ",params={"hashtag"})
    public String getFilteredPosts(Model model, @RequestParam String Hashtag){
        model.addAttribute("questions",questionService.findAllByHashTag(Hashtag));
        return "questions/index";
    }


    @PostMapping("")
    public String createPost(QuestionSaveRequestDTO QuestionSaveRequestDTO, HttpSession httpSession,
                             @RequestParam(value="hashtags",defaultValue = "false")List<String> hashtag){
        questionService.save(QuestionSaveRequestDTO, httpSession,hashtag);
        return "redirect:/questions";
    }

    @GetMapping("/form") // 질문 작성 게시글에 hashtag 입력 양식
    public String getQuestionForm(Model model){
        model.addAttribute("hashtags",questionService.findHashTags());
        return "questions/form";
    }
}
