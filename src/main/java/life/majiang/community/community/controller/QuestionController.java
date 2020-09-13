package life.majiang.community.community.controller;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Shawn on 2020/6/13 19:06.
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{questionId}")
    public String question(HttpServletRequest request,
                           @PathVariable(name = "questionId") Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
