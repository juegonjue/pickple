package com.se.pickple_api_server.v1.oauth.infra.api;

import com.se.pickple_api_server.v1.account.domain.usecase.AccountCreateUseCase;
import com.se.pickple_api_server.v1.account.domain.usecase.AccountReadUseCase;
import com.se.pickple_api_server.v1.oauth.domain.service.HttpRequestService;
import com.se.pickple_api_server.v1.oauth.domain.service.OauthService;
import com.se.pickple_api_server.v1.oauth.infra.dto.token.OauthTokenResponse;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import com.se.pickple_api_server.v1.oauth.infra.oauth.OauthType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;
    private final HttpServletResponse response;
    private final AccountReadUseCase accountReadUseCase;
    private final AccountCreateUseCase accountCreateUseCase;

    @GetMapping(value = "/{socialLoginType}")
    public void redirect(
            @PathVariable(name = "socialLoginType") String type) throws IOException {
        OauthType oauthType = OauthType.valueOf(type.toUpperCase());
        response.sendRedirect(oauthService.getRedirectUrl(oauthType));
    }



    @GetMapping(value = "/{socialLoginType}/callback")
    public String callback(@PathVariable(name = "socialLoginType") String type
                           ,HttpServletRequest httpServletRequest) throws IOException {
        OauthType oauthType = OauthType.valueOf(type.toUpperCase());
        OauthTokenResponse oauthTokenResponse =  oauthService.getTokenResponseDto(oauthType, httpServletRequest);
        OauthUserInfo oauthUserInfo = oauthService.getUserInfo(oauthType, oauthTokenResponse);
        if(!accountReadUseCase.isExist(Long.parseLong(oauthUserInfo.getId())))
            accountCreateUseCase.signUpBySocial(oauthUserInfo);



        return null;
//        response.sendRedirect(naverOauth.getOauthRedirectURL());
    }
}
