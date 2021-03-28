package com.se.pickple_api_server.v1.oauth.domain.service;

import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.oauth.domain.error.OauthErrorCode;
import com.se.pickple_api_server.v1.oauth.infra.dto.token.OauthTokenResponse;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import com.se.pickple_api_server.v1.oauth.infra.oauth.OauthType;
import com.se.pickple_api_server.v1.oauth.infra.oauth.SocialOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;

    public String getRedirectUrl(OauthType socialLoginType) {
        SocialOauth socialOauth = findSocialOauthByType(socialLoginType);
        return socialOauth.getOauthRedirectURL();
    }

    private SocialOauth findSocialOauthByType(OauthType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new BusinessException(OauthErrorCode.NO_SUCH_OAUTH));
    }

    public OauthTokenResponse getTokenResponseDto(OauthType socialLoginType, HttpServletRequest httpServletRequest) {
        SocialOauth socialOauth = findSocialOauthByType(socialLoginType);
        return socialOauth.getTokenResponseDto(httpServletRequest);
    }

    public OauthUserInfo getUserInfo(OauthType oauthType, OauthTokenResponse oauthTokenResponse) {
        SocialOauth socialOauth = findSocialOauthByType(oauthType);
        return socialOauth.getUserInfo(oauthTokenResponse);
    }
}
