package com.se.pickple_api_server.v1.common.infra.dto.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.SecureRandom;

@RestController("/naver")
public class NaverLoginController {

    private String CLIENT_ID = "buzoWriDgkB4orQbjYPK"; //애플리케이션 클라이언트 아이디값";
    private String CLI_SECRET = "UjpIyaq9Ty"; //애플리케이션 클라이언트 시크릿값";

    // 상태 토큰 생성
    public String generateState() {

        SecureRandom random = new SecureRandom();
        return new BigInteger(130,random).toString(32);
    }


    /*
     * 넘어오는 과정 *

         1. 인증 요청문 생성 (네이버 로그인페이지 요청)
         [인증 요청문 : reqAuthUri] https://nid.naver.com/oauth2.0/authorize?client_id={CLIENT_ID}&response_type=code&redirect_uri=http://localhost8080/callback&state={state토큰}

         2. 성공/실패 시 콜백 url로 넘어온다
         [성공] http://localhost8080/naver/callback?code={코드값}&state={state값}
         [실패] http://localhost8080/naver/callback?state={state값}&error={에러코드값}&error_description={에러메시지}

         [콜백 url:callbackUri] http://localhost8080/naver/callback
         *
    */
    @RequestMapping("/naver")
    public String testNaver(HttpSession session, Model model) throws UnsupportedEncodingException, UnknownHostException {
        String state = generateState();
        String callbackUri = "http://localhost8080/naver/callback";
        String reqAuthUri = "https://nid.naver.com/oauth2.0/authorize?";

//        String redirectURI = URLEncoder.encode("http://localhost:8080/naver/callback", "UTF-8");
//        SecureRandom random = new SecureRandom();
//        String state = new BigInteger(130, random).toString();
//        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
//        apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
//                CLIENT_ID, redirectURI, state);
//        session.setAttribute("state", state);
//        model.addAttribute("apiURL", apiURL);
           return "test-naver"; //수정필요
    }
    /**
//     * 콜백 페이지 컨트롤러
//     * @param session
//     * @param request
//     * @param model
//     * @return
//     * @throws IOException
//     * @throws ParseException
//     */
//    @RequestMapping("/naver/callback1")
//    public String naverCallback1(HttpSession session, HttpServletRequest request, Model model) throws IOException, ParseException {
//        String code = request.getParameter("code");
//        String state = request.getParameter("state");
//        String redirectURI = URLEncoder.encode("http://localhost:8080/naver/callback1", "UTF-8");
//        String apiURL;
//        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
//        apiURL += "client_id=" + CLIENT_ID;
//        apiURL += "&client_secret=" + CLI_SECRET;
//        apiURL += "&redirect_uri=" + redirectURI;
//        apiURL += "&code=" + code;
//        apiURL += "&state=" + state;
//        System.out.println("apiURL=" + apiURL);
//        String res = requestToServer(apiURL);
//        if(res != null && !res.equals("")) {
//            model.addAttribute("res", res);
//            Map<String, Object> parsedJson = new JSONParser(res).parseObject();
//            System.out.println(parsedJson);
//            session.setAttribute("currentUser", res);
//            session.setAttribute("currentAT", parsedJson.get("access_token"));
//            session.setAttribute("currentRT", parsedJson.get("refresh_token"));
//        } else {
//            model.addAttribute("res", "Login failed!");
//        }
//        return "test-naver-callback";
//    }
//    /**
//     * 토큰 갱신 요청 페이지 컨트롤러
//     * @param session
//     * @param request
//     * @param model
//     * @param refreshToken
//     * @return
//     * @throws IOException
//     * @throws ParseException
//     */
//    @RequestMapping("/naver/refreshToken")
//    public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken) throws IOException, ParseException {
//        String apiURL;
//        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
//        apiURL += "client_id=" + CLIENT_ID;
//        apiURL += "&client_secret=" + CLI_SECRET;
//        apiURL += "&refresh_token=" + refreshToken;
//        System.out.println("apiURL=" + apiURL);
//        String res = requestToServer(apiURL);
//        model.addAttribute("res", res);
//        session.invalidate();
//        return "test-naver-callback";
//    }
//    /**
//     * 토큰 삭제 컨트롤러
//     * @param session
//     * @param request
//     * @param model
//     * @param accessToken
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping("/naver/deleteToken")
//    public String deleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken) throws IOException {
//        String apiURL;
//        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
//        apiURL += "client_id=" + CLIENT_ID;
//        apiURL += "&client_secret=" + CLI_SECRET;
//        apiURL += "&access_token=" + accessToken;
//        apiURL += "&service_provider=NAVER";
//        System.out.println("apiURL=" + apiURL);
//        String res = requestToServer(apiURL);
//        model.addAttribute("res", res);
//        session.invalidate();
//        return "test-naver-callback";
//    }
//    /**
//     * 액세스 토큰으로 네이버에서 프로필 받기
//     * @param accessToken
//     * @return
//     * @throws IOException
//     */
//    @ResponseBody
//    @RequestMapping("/naver/getProfile")
//    public String getProfileFromNaver(String accessToken) throws IOException {
//        // 네이버 로그인 접근 토큰;
//        String apiURL = "https://openapi.naver.com/v1/nid/me";
//        String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
//        String res = requestToServer(apiURL, headerStr);
//        return res;
//    }
//    /**
//     * 세션 무효화(로그아웃)
//     * @param session
//     * @return
//     */
//    @RequestMapping("/naver/invalidate")
//    public String invalidateSession(HttpSession session) {
//        session.invalidate();
//        return "redirect:/naver";
//    }
//    /**
//     * 서버 통신 메소드
//     * @param apiURL
//     * @return
//     * @throws IOException
//     */
//    private String requestToServer(String apiURL) throws IOException {
//        return requestToServer(apiURL, "");
//    }
//    /**
//     * 서버 통신 메소드
//     * @param apiURL
//     * @param headerStr
//     * @return
//     * @throws IOException
//     */
//    private String requestToServer(String apiURL, String headerStr) throws IOException {
//        URL url = new URL(apiURL);
//        HttpURLConnection con = (HttpURLConnection)url.openConnection();
//        con.setRequestMethod("GET");
//        System.out.println("header Str: " + headerStr);
//        if(headerStr != null && !headerStr.equals("") ) {
//            con.setRequestProperty("Authorization", headerStr);
//        }
//        int responseCode = con.getResponseCode();
//        BufferedReader br;
//        System.out.println("responseCode="+responseCode);
//        if(responseCode == 200) { // 정상 호출
//            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        } else {  // 에러 발생
//            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//        }
//        String inputLine;
//        StringBuffer res = new StringBuffer();
//        while ((inputLine = br.readLine()) != null) {
//            res.append(inputLine);
//        }
//        br.close();
//        if(responseCode==200) {
//            return res.toString();
//        } else {
//            return null;
//        }
//    }
}