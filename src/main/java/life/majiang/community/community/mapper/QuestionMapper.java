package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Shawn on 2020/3/3 21:05.
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into Question(title, description, gmt_create, gmt_modified, creator, comment_count, view_count, like_count, tag)" +
            "values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{commentCount}, #{viewCount}, #{likeCount}, #{tag})")
    @ResultMap("questionMap")
    public void createQuestion(Question question);

    @Select("select * from Question order by gmt_modified desc limit #{size} offset #{offset} ")
    @Results(id = "questionMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "description", property = "description"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified"),
            @Result(column = "creator", property = "creator"),
            @Result(column = "comment_count", property = "commentCount"),
            @Result(column = "view_count", property = "viewCount"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "tag", property = "tag"),

    })
    public List<Question> getAllQuestions(Integer offset, Integer size);


    @Select("select * from Question where creator = #{creator} order by gmt_modified desc limit #{size} offset #{offset} ")
    @ResultMap("questionMap")
    public List<Question> getUserQuestions(Integer creator, Integer offset, Integer size);

    @Select("select count(*) from question")
    int count();

    @Select("select count(*) from question where creator = #{creator}")
    int countByCreator(Integer creator);

    @Select("select * from question where id = #{id}")
    Question getQuestionById(Integer id);

    @Update("update question set description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} " +
            "where id = #{id}")
    void updateQuestion(Question question);
}
