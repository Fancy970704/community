package life.majiang.community.community.controller;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class
IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping({"/index", "/"})
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page) {
        PaginationDTO paginationDTO = questionService.list(page);  //问题列表
        model.addAttribute("paginationDTO", paginationDTO);   //与session功能类似
        return "index";
    }
}
