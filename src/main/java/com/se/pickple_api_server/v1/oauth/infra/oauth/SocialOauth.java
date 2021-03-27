package com.se.pickple_api_server.v1.oauth.infra.oauth;

import com.se.pickple_api_server.v1.oauth.infra.dto.token.OauthTokenResponse;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;

import javax.servlet.http.HttpServletRequest;

public interface SocialOauth {

    String getOauthRedirectURL();

    OauthType type();

    OauthTokenResponse getTokenResponseDto(HttpServletRequest httpServletRequest);

    OauthUserInfo getUserInfo(OauthTokenResponse oauthTokenResponse);
}
