package life.majiang.community.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by Shawn on 2/19/2020 8:30 PM.
 */
@Component
public class GithubProvider {
    private static final Logger logger = LoggerFactory.getLogger(GithubProvider.class);
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String response = restTemplate.postForObject("https://github.com/login/oauth/access_token", accessTokenDTO, String.class);
        logger.debug("response body: {}",response);
        String[] strings = response.split("&");
        String token = strings[0].split("=")[1];
        logger.info("exchanged Accesstoken:" + token);
        return token;
    }

    public GithubUser getUser(String githubAccessToken) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/vnd.github.v3+json");
        headers.add("Authorization", "token "+githubAccessToken);
        HttpEntity<GithubUser> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<GithubUser> responseEntity = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, requestEntity, GithubUser.class);
        return responseEntity.getBody();
    }

}
