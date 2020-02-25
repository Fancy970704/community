package life.majiang.community.community.mapper;

import life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Shawn on 2/25/2020 4:59 PM.
 */

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified) " +
            "values(#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified})")
    public void insertUser(User user);
}
