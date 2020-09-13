package life.majiang.community.community.service;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.UUID;

/**
 * Created by Shawn on 2020/7/5 19:44.
 */
@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user){
        User dbUser = null;
        if((dbUser = userMapper.findByAccountId(String.valueOf(user.getAccountId())))!=null)
        {
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setBio(user.getBio());
            dbUser.setName(user.getName());
            userMapper.updateUser(dbUser);
        }
        else
        {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insertUser(user);
        }
    }
}
