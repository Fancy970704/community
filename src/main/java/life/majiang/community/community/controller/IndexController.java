package life.majiang.community.community.controller;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper mapper;
    @GetMapping({"/index", "/"})
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            String token = null;
            for(Cookie cookie: cookies)
            {
                if(cookie.getName().equals("token"))
                {
                    token = cookie.getValue();
                    System.out.println("cookie acquired "+token);
                }
            }
            User user = mapper.findByToken(token);
            if(user!=null)
            {
                System.out.println(user.toString());
                request.getSession().setAttribute("user", user);
            }

        }
        return "index";
    }
}
