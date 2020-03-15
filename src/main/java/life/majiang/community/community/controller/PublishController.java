package life.majiang.community.community.controller;

import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Shawn on 2020/2/29 20:21.
 */
@Controller
public class PublishController {
    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
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
            System.out.println(user.toString());
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            System.out.println("creator: "+user.getId());
            question.setCreator(new Integer(user.getId()));
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.createQuestion(question);
            return "redirect:/";
        }
    }
}
