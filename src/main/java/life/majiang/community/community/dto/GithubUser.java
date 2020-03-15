package life.majiang.community.community.dto;

import lombok.Data;

/**
 * Created by Shawn on 2/19/2020 10:13 PM.
 */
@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    private String avatar_url;

}
