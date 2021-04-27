package com.se.pickple_api_server.v1.account.infra.api;

import com.se.pickple_api_server.v1.account.application.dto.AccountDeleteDto;
import com.se.pickple_api_server.v1.account.application.service.*;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import com.se.pickple_api_server.v1.oauth.infra.oauth.OauthType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "사용자 관리")
public class AccountApiController {

    private final AccountCreateService accountCreateService;
    private final AccountSignInService accountSignInService;
    private final AccountDeleteService accountDeleteService;
    private final AccountReadService accountReadService;
    private final AccountSocialService accountSocialService;

    // UC-AC-01 회원 등록
    // UC-AC-02 로그인
    // request body : access token
    // --> response : 사용자정보 있으면 HttpStatus.OK
    @PostMapping(value = "/{socialLoginType}")
    @ApiOperation(value = "소셜 회원가입 및 로그인")
    public SuccessResponse accessToken(@PathVariable(name="socialLoginType") String type, @RequestBody String clientToken){
        // 해당 토큰을 네이버에 보내서 사용자 정보 얻음
        OauthType oauthType = OauthType.valueOf(type.toUpperCase());
        OauthUserInfo oauthUserInfo = accountSocialService.getUserInfo(oauthType, clientToken);
        if (accountSocialService.isExist(oauthUserInfo.getId())) {
            Long id = accountCreateService.signUpBySocial(oauthUserInfo,oauthType);
            if (id != null) {
                return new SuccessResponse<>(HttpStatus.OK.value(), "성공적으로 로그인 되었습니다.", accountSignInService.signIn(id));
            }
        }

        return null;
    }


    //UC-AC-01 회원 등록
//    @ApiOperation(value = "회원 가입")
//    @PostMapping(path="/signup")
//    @ApiResponses(value = {
//            @ApiResponse(code = 400, message = "존재하지 않는 사용자"),
//            @ApiResponse(code = 400, message = "비밀번호 불일치")
//    })
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public SuccessResponse<Long> signUp(@RequestBody @Validated AccountCreateDto.Request request, HttpServletRequest httpServletRequest) {
//        return new SuccessResponse<>(HttpStatus.CREATED.value(), "회원가입에 성공했습니다.",
//                accountCreateService.signUp(request));
//    }

    // UC-AC-02 로그인
//    @ApiOperation(value = "로그인")
//    @PostMapping(path = "/signin")
//    @ApiResponses(value = {
//            @ApiResponse(code = 400, message = "비밀번호 불일치")
//    })
//    @ResponseStatus(value = HttpStatus.OK)
//    public SuccessResponse<AccountSignInDto.Response> singIn(@RequestBody @Validated AccountSignInDto.Request request) {
//        return new SuccessResponse<>(HttpStatus.OK.value(), "성공적으로 로그인 되었습니다.",
//                accountSignInService.signIn(request.getId(), request.getPw()));
//    }

    // UC-AC-03 로그아웃


    // UC-AC-04 회원탈퇴
    @DeleteMapping(path = "/account")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "회원 탈퇴")
    public SuccessResponse deleteAccount(@RequestBody @Validated AccountDeleteDto.Request request) {
        accountDeleteService.delete(request);
        return new SuccessResponse(HttpStatus.OK.value(),"성공적으로 삭제되었습니다.");
    }

    // UC-AC-05 이메일 인증
    // UC-AC-06 사용자 목록 조회
    // UC-AC-07 회원 정보 조회

    // UC-AC-08 회원 정보 검색{idString}
    //public SuccessResponse

}