package life.majiang.community.community.dto;

import lombok.Data;

/**
 * Created by Shawn on 2/19/2020 8:33 PM.
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
