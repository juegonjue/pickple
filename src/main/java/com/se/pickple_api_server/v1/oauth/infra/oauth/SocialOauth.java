package com.se.pickple_api_server.v1.oauth.infra.oauth;

import com.se.pickple_api_server.v1.oauth.infra.dto.token.OauthTokenResponse;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;

import javax.servlet.http.HttpServletRequest;

public interface SocialOauth {

    //String getOauthRedirectURL();
    //OauthTokenResponse getTokenResponseDto(HttpServletRequest httpServletRequest);

    // social에서 access token을 string값으로 받아옴
    OauthUserInfo getUserInfo(String token);

    OauthType type();

}
