package life.majiang.community.community.model;

import lombok.Data;

/**
 * Created by Shawn on 2020/3/3 21:07.
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
}
