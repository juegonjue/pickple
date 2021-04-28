//package com.se.pickple_api_server.v1.oauth.infra.api;
//
//import com.se.pickple_api_server.v1.account.application.service.AccountCreateService;
//import com.se.pickple_api_server.v1.account.application.service.AccountReadService;
//import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
//import com.se.pickple_api_server.v1.oauth.domain.service.OauthService;
//import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
//import com.se.pickple_api_server.v1.oauth.infra.oauth.OauthType;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class OauthController {
//
//    private final OauthService oauthService;
//    private final HttpServletResponse response;
//    private final AccountReadService accountReadService;
//    //private final AccountCreateService accountCreateService;
//
//
//    // request body : access token
//    // --> response : 사용자정보 있으면 HttpStatus.OK
//    @PostMapping(value = "/{socialLoginType}")
//    @ApiOperation(value = "소셜 회원가입 및 로그인")
//    public SuccessResponse accessToken(@PathVariable(name="socialLoginType") String type, @RequestBody String clientToken){
//        // 해당 토큰을 네이버에 보내서 사용자 정보 얻음
//        OauthType oauthType = OauthType.valueOf(type.toUpperCase());
//        OauthUserInfo oauthUserInfo = oauthService.getUserInfo(oauthType, clientToken);
//
//        // DB에 회원정보 있는지 파악
//        if (oauthUserInfo != null) {
//            accountReadService.isExistIdString(oauthUserInfo.getId());
//
//        }
//
//        //System.out.println(oauthUserInfo.getId());
//        if (accountReadService.isExist(Long.parseLong(oauthUserInfo.getId())))
//            return new SuccessResponse(HttpStatus.OK.value(), "가입된 계정이 있습니다.");
//        // (사용자정보를 레포에서 확인해야 signin할지, signup할지 확인가능)
//        // 사용자정보에서 id 얻은 뒤 레포지토리에 해당 id 있는지 확인
//        return null;
//    }
//
//
//
////    @GetMapping(value = "/{socialLoginType}")
////    public void redirect(
////            @PathVariable(name = "socialLoginType") String type) throws IOException {
////        OauthType oauthType = OauthType.valueOf(type.toUpperCase());
////        response.sendRedirect(oauthService.getRedirectUrl(oauthType));
////    }
//
////    @GetMapping(value = "/{socialLoginType}/callback")
////    public String callback(@PathVariable(name = "socialLoginType") String type
////                           ,HttpServletRequest httpServletRequest) throws IOException {
////        OauthType oauthType = OauthType.valueOf(type.toUpperCase());
////        OauthTokenResponse oauthTokenResponse =  oauthService.getTokenResponseDto(oauthType, httpServletRequest);
////        OauthUserInfo oauthUserInfo = oauthService.getUserInfo(oauthType, oauthTokenResponse);
////        if(!accountReadService.isExist(Long.parseLong(oauthUserInfo.getId())))
////            accountCreateService.signUpBySocial(oauthUserInfo);
////
////        return null;
//////        response.sendRedirect(naverOauth.getOauthRedirectURL());
////    }
//}
