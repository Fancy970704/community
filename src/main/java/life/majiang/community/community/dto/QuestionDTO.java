package life.majiang.community.community.dto;

import life.majiang.community.community.model.User;
import lombok.Data;

/**
 * Created by Shawn on 2020/3/11 20:53.
 */
@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private User user;
}
