package com.se.pickple_api_server.v1.oauth.infra.oauth;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pickple_api_server.v1.oauth.domain.service.HttpRequestService;
//import com.se.pickple_api_server.v1.oauth.infra.dto.token.NaverOauthTokenResponse;
import com.se.pickple_api_server.v1.oauth.infra.dto.token.OauthTokenResponse;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.NaverUserInfo;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Component
public class NaverOauth implements SocialOauth {
//
//    @Value("${spring.oauth.naver.client}")
//    private String CLIENT_ID;
//    @Value("${spring.oauth.naver.secret}")
//    private String SECRET_KEY;
//
    @Autowired
    private HttpRequestService httpRequestService;
//
//    @Override
//    public String getOauthRedirectURL() {
//        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
//
//        try {
//            String redirectURI = URLEncoder.encode("http://localhost:8080/api/auth/naver/callback", "UTF-8");
//            SecureRandom random = new SecureRandom();
//            String state = new BigInteger(130, random).toString();
//            apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s", CLIENT_ID, redirectURI, state);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return apiURL;
//    }
//
//    @Override
//    public OauthTokenResponse getTokenResponseDto(HttpServletRequest httpServletRequest) {
//        String code = httpServletRequest.getParameter("code");
//        String state = httpServletRequest.getParameter("state");
//        String apiURL = null;
//        try{
//            String redirectURI = URLEncoder.encode("http://localhost:8080/api/auth/naver/callback", "UTF-8");
//            apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
//            apiURL += "client_id=" + CLIENT_ID;
//            apiURL += "&client_secret=" + SECRET_KEY;
//            apiURL += "&redirect_uri=" + redirectURI;
//            apiURL += "&code=" + code;
//            apiURL += "&state=" + state;
//            String res = httpRequestService.requestGet(apiURL);
//            ObjectMapper objectMapper = new ObjectMapper();
//            NaverOauthTokenResponse naverTokenResponse = objectMapper.readValue(res, NaverOauthTokenResponse.class);
//            return naverTokenResponse;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

    //TODO 사용자 정보 받아오기
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
            System.out.println(naverUserInfo.getId());
            System.out.println(naverUserInfo.getEmail());
            System.out.println(naverUserInfo.getName());
            return naverUserInfo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
//
//    @Override
//    public OauthUserInfo getUserInfo(OauthTokenResponse oauthTokenResponse) {
//        String token = oauthTokenResponse.getAccessToken();
//        String header = "Bearer " + token; // Bearer 다음에 공백 추가
//        String apiURL = "https://openapi.naver.com/v1/nid/me";
//        Map<String, String> requestHeaders = new HashMap<>();
//        requestHeaders.put("Authorization", header);
//
//        String responseBody = httpRequestService.requestGetWithHeaders(apiURL,requestHeaders);
//        System.out.println(responseBody);
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            NaverUserInfo naverUserInfo = objectMapper.readValue(responseBody, NaverUserInfo.class);
//            System.out.println(naverUserInfo.getId());
//            return naverUserInfo;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public OauthType type() {
        return OauthType.NAVER;
    }

}

