package life.majiang.community.community.interceptor;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Shawn on 2020/5/24 20:28.
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
    @Autowired
    UserMapper mapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();    //获取当前网页的所有cookie
        if(cookies!=null){
            String token = null;
            for(Cookie cookie: cookies)
            {
                if(cookie.getName().equals("token"))    //若有名为token的cookie，则说明有当前用户登录信息
                {
                    token = cookie.getValue();
                    break;
                }
            }
            User user = mapper.findByToken(token);  //根据token去数据库获取User对象
            if(user!=null)
            {
                request.getSession().setAttribute("user", user);    //将User对象放入session以便获取信息

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
