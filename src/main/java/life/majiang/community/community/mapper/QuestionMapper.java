package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Shawn on 2020/3/3 21:05.
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into Question(id, title, description, gmt_create, gmt_modified, creator, comment_count, view_count, like_count, tag)" +
            "values(#{id}, #{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{commentCount}, #{viewCount}, #{likeCount}, #{tag})")
    public void createQuestion(Question question);
}
