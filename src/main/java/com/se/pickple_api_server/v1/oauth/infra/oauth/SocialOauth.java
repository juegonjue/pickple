package com.se.pickple_api_server.v1.oauth.infra.oauth;

import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;

public interface SocialOauth {
    OauthUserInfo getUserInfo(String token);
    OauthType type();
}
