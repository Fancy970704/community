package life.majiang.community.community.mapper;

import com.sun.tracing.dtrace.ProviderAttributes;
import life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by Shawn on 2/25/2020 4:59 PM.
 */

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified) " +
            "values(#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified})")
    public void insertUser(User user);


    @Select("select * from USER where token = #{token}")
    @Results(id="userMap", value=
            {
                    //id字段默认为false，表示不是主键
                    //column表示数据库字段，property表示实体类属性名
                    @Result(column="account_id", property="accountId"),
                    @Result(column="name", property="name"),
                    @Result(column="token", property="token"),
                    @Result(column="gmt_create", property="gmtCreate"),
                    @Result(column="gmt_modified", property="gmtModified"),
            })
    public User findByToken(@Param("token") String token);
}
