package com.se.pickple_api_server.v1.oauth.domain.error;

import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import lombok.Getter;

@Getter
public enum OauthErrorCode implements ErrorCode {

    NO_SUCH_OAUTH(400, "OA01", "지원하지 않는 플랫폼");
    private int status;
    private final String code;
    private final String message;

    OauthErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
