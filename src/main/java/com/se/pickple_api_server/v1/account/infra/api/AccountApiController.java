package com.se.pickple_api_server.v1.account.infra.api;

import com.se.pickple_api_server.v1.account.application.dto.*;
import com.se.pickple_api_server.v1.account.application.service.*;
import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import com.se.pickple_api_server.v1.oauth.infra.oauth.OauthType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final AccountUpdateService accountUpdateService;

    // test회원가입 -추후삭제
    @ApiOperation(value = "(test) 일반 회원 가입")
    @PostMapping(path="/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> signUp(@RequestBody @Validated AccountCreateDto.Request request, HttpServletRequest httpServletRequest) {
        return new SuccessResponse<>(HttpStatus.CREATED.value(), "회원가입에 성공했습니다.",
                accountCreateService.signUp(request));
    }


    // test로그인 -추후삭제
    @ApiOperation(value = "(test) 일반 로그인")
    @PostMapping(path = "/signin")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<AccountSignInDto.Response> singIn(@RequestBody @Validated AccountSignInDto.RequestTest request) {
        return new SuccessResponse<>(HttpStatus.OK.value(), "성공적으로 로그인 되었습니다.",
                accountSignInService.signIn(request.getIdString()));
    }


    // UC-AC-02 소셜 회원가입 및 로그인
    // request body : access token
    // --> response : 사용자정보 있으면 HttpStatus.OK
    @PostMapping(value = "/signin/{socialLoginType}")
    @ApiOperation(value = "소셜 회원가입 및 로그인")
    public SuccessResponse signIn(@PathVariable(name="socialLoginType") String type, @RequestBody String clientToken){
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
    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping(path = "/account")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse deleteAccount(@RequestBody @Validated AccountDeleteDto.Request request) {
        accountDeleteService.delete(request);
        return new SuccessResponse(HttpStatus.OK.value(),"성공적으로 삭제되었습니다.");
    }

    // UC-AC-05 이메일 인증

    // UC-AC-06 사용자 목록 조회 (페이징)
    @ApiOperation(value = "[관리자] 사용자 목록 조회 (페이징)")
    @GetMapping(path = "/account")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readAllAccount(@Validated PageRequest pageRequest){
        return new SuccessResponse(HttpStatus.OK.value(), "성공적으로 사용자 목록 조회 페이징", accountReadService.readAll(pageRequest.of()));
    }


    // UC-AC-07 회원 정보 조회 (관리자)
    @ApiOperation(value = "[관리자] 사용자 idString로 회원 조회")
    @GetMapping(path = "/account/{idString}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<AccountReadDto.Response> readAccountById(@PathVariable(name = "idString") String idString) {
        return new SuccessResponse(HttpStatus.OK.value(),"성공적으로 사용자 정보를 조회하였습니다.", accountReadService.readByIdString(idString));
    }

    // UC-AC-08 회원 검색 및 페이징
    // public SuccessResponse

    // 유저로딩 부분
    @ApiOperation(value = "[유저로딩]내 정보 요청")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @GetMapping(path = "/account/userloading")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<AccountReadDto.Response> userLoading() {
        return new SuccessResponse(HttpStatus.OK.value(), "userloading에 성공하였습니다.", accountReadService.getUserloading());
    }

    // 회원 수정
//    @ApiOperation(value = "회원 수정")
//    @PutMapping(path = "/account")
//    @ResponseStatus(value = HttpStatus.OK)
//    public SuccessResponse updateAccount(@RequestBody @Validated AccountUpdateDto.Request request) {
//        return new SuccessResponse(HttpStatus.OK.value(),"",accountUpdateService.update(request));
//    }
}