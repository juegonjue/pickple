//package com.se.pickple_api_server.v1.common.infra.dto.api;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//import java.io.UnsupportedEncodingException;
//import java.math.BigInteger;
//import java.net.URLEncoder;
//import java.net.UnknownHostException;
//import java.security.SecureRandom;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/naver")
//public class NaverApiController {
//
//    private final NaverToken
//    private String CLIENT_ID = "buzoWriDgkB4orQbjYPK"; //애플리케이션 클라이언트 아이디값";
//    private String CLIENT_SECRET = "UjpIyaq9Ty"; //애플리케이션 클라이언트 시크릿값";
//
//    public String testNaver(HttpSession session) throws UnsupportedEncodingException, UnknownHostException {
//        String redirectURI = URLEncoder.encode("http://localhost:8080/naver/callback1", "UTF-8");
//        SecureRandom random = new SecureRandom();
//        String state = new BigInteger(130, random).toString();
//        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
//        apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
//                CLIENT_ID, redirectURI, state);
//        session.setAttribute("state", state);
//        model.addAttribute("apiURL", apiURL);
//        return "test-naver";
//    }
//}
