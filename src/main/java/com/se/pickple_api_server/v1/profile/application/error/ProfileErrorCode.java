package com.se.pickple_api_server.v1.profile.application.error;

import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import lombok.Getter;

@Getter
public enum ProfileErrorCode implements ErrorCode {
    NO_SUCH_PROFILE(400, "PF01", "존재하지 않는 프로필"),
    INVALID_INPUT(401, "PF02", "입력값이 올바르지 않음"),
    ALREADY_EXIST(401, "PF03", "프로필이 이미 존재함"),
    DUPLICATED_KAKAOID(401, "PF04", "중복된 카카오아이디 이미 존재함"),
    DUPLICATED_WORKEMAIL(401, "PF05", "중복된 업무이메일 이미 존재함"),
    DUPLICATED_BLOG(401, "PF06", "중복된 블로그주소 이미 존재함");

    private int status;
    private final String code;
    private final String message;

    ProfileErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
