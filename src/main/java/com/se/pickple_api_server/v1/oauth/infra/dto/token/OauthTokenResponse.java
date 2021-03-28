package com.se.pickple_api_server.v1.oauth.infra.dto.token;

public interface OauthTokenResponse {
    String getAccessToken();
    String getRefreshToken();
    String getTokenType();
    Long getExpiresIn();
}
