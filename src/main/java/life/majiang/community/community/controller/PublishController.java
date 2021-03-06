package life.majiang.community.community.controller;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;
import life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Shawn on 2020/2/29 20:21.
 */
@Controller
public class PublishController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id,
                       Model model){
        QuestionDTO question = questionService.getQuestionById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id", id);
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null || title.equals(""))
        {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null || description.equals(""))
        {
            model.addAttribute("error","内容不能为空");
            return "publish";
        }

        User user = (User)(request.getSession().getAttribute("user"));
        if(user==null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        else
        {
            Question question = new Question();
            question.setId(id);
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            questionService.createOrUpdateQuestion(question);
            return "redirect:/";
        }
    }


}
