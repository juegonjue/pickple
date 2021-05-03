package com.se.pickple_api_server.v1.account.infra.api;

import com.se.pickple_api_server.v1.account.application.dto.AccountCreateDto;
import com.se.pickple_api_server.v1.account.application.dto.AccountDeleteDto;
import com.se.pickple_api_server.v1.account.application.dto.AccountReadDto;
import com.se.pickple_api_server.v1.account.application.dto.AccountSignInDto;
import com.se.pickple_api_server.v1.account.application.service.*;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import com.se.pickple_api_server.v1.oauth.infra.oauth.OauthType;
import com.sun.net.httpserver.Authenticator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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

    // test회원가입 -추후삭제
    @ApiOperation(value = "(test) 일반 회원 가입")
    @PostMapping(path="/signup")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "존재하지 않는 사용자"),
            @ApiResponse(code = 400, message = "비밀번호 불일치")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> signUp(@RequestBody @Validated AccountCreateDto.Request request, HttpServletRequest httpServletRequest) {
        return new SuccessResponse<>(HttpStatus.CREATED.value(), "회원가입에 성공했습니다.",
                accountCreateService.signUp(request));
    }


    // test로그인 -추후삭제
    @ApiOperation(value = "(test) 일반 로그인")
    @PostMapping(path = "/signin")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "비밀번호 불일치")
    })
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<AccountSignInDto.Response> singIn(@RequestBody @Validated AccountSignInDto.RequestTest request) {
        return new SuccessResponse<>(HttpStatus.OK.value(), "성공적으로 로그인 되었습니다.",
                accountSignInService.signInString(request.getId()));
    }


    // UC-AC-02 소셜 회원가입 및 로그인
    // request body : access token
    // --> response : 사용자정보 있으면 HttpStatus.OK
    @PostMapping(value = "/signin/{socialLoginType}")
    @ApiOperation(value = "소셜 회원가입 및 로그인")
    public SuccessResponse accessToken(@PathVariable(name="socialLoginType") String type, @RequestBody String clientToken){
        // 해당 토큰을 네이버에 보내서 사용자 정보 얻음
        OauthType oauthType = OauthType.valueOf(type.toUpperCase());
        OauthUserInfo oauthUserInfo = accountSocialService.getUserInfo(oauthType, clientToken);
        Long id = null;
        if (!accountSocialService.isExist(oauthUserInfo.getId())) {
            id = accountCreateService.signUpBySocial(oauthUserInfo,oauthType);
            System.out.println("Controller. 등록되지 않은 아이디 -> 회원가입 : " + id);
        }
        if (accountSocialService.isExist(oauthUserInfo.getId())) {
            id = accountReadService.findIdByIdString(oauthUserInfo.getId());
            System.out.println("Controller. 등록된 아이디 -> 로그인 : " + id);
        }
        return new SuccessResponse<>(HttpStatus.OK.value(), "성공적으로 로그인 되었습니다.", accountSignInService.signIn(id));
    }


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
    @ApiOperation(value = "사용자 아이디로 회원 조회")
    @GetMapping(path = "/account/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<AccountReadDto.Response> readAccountById(@PathVariable(name = "accountId") Long id) {
        return new SuccessResponse(HttpStatus.OK.value(),"성공적으로 사용자 정보를 조회하였습니다.", accountReadService.readById(id));
    }
    // UC-AC-08 회원 검색 {idString}
    //public SuccessResponse

    // 토큰으로 회원 정보 얻기
//    @ApiOperation(value = "사용자 토큰으로 회원 정보 조회")
//    @GetMapping(path="/account/token/{token}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public SuccessResponse<AccountReadDto.Response> readAccountByToken(@PathVariable(name = "token") String token) {
//        if (!accountReadService.isValidateToken(token)) {
//            return new SuccessResponse(HttpStatus.OK.alue(), "호에엥", accountReadService.getUserInfoByToken(token));
//        }
//        return new SuccessResponse(HttpStatus.OK.value(), "토큰으로 사용자 정보를 조회하였습니다.", accountReadService.getUserInfoByToken(token));
//    }
//    @ApiOperation(value = "내 정보 요청")
//    @GetMapping(path = "/account/myInfo")
//    @ResponseStatus(value = HttpStatus.OK)
//    public SuccessResponse<AccountReadDto.Response> setClientPageUserInfo() {
//
//    }
}