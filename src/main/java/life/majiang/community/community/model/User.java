package life.majiang.community.community.model;

import lombok.Data;

/**
 * Created by Shawn on 2/25/2020 4:50 PM.
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}
