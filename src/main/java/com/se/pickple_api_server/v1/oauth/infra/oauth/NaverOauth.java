package com.se.pickple_api_server.v1.oauth.infra.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pickple_api_server.v1.oauth.domain.service.HttpRequestService;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.NaverUserInfo;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class NaverOauth implements SocialOauth {

    @Autowired
    private HttpRequestService httpRequestService;

    @Override
    public OauthUserInfo getUserInfo(String token) {
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);

        String responseBody = httpRequestService.requestGetWithHeaders(apiURL,requestHeaders);
        System.out.println(responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NaverUserInfo naverUserInfo = objectMapper.readValue(responseBody, NaverUserInfo.class);
            System.out.println("토큰 : " + token);
            System.out.println("아이디 : " + naverUserInfo.getId());
            System.out.println("이메일 : " + naverUserInfo.getEmail());
            System.out.println("이름 : " + naverUserInfo.getName());
            return naverUserInfo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OauthType type() {
        return OauthType.NAVER;
    }

}

