package com.se.pickple_api_server.v1.account.application.error;

import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import lombok.Getter;

@Getter
public enum AccountErrorCode implements ErrorCode {

    NO_SUCH_ACCOUNT(400, "AC01", "존재하지 않는 사용자"),
    PASSWORD_INCORRECT(401, "AC02", "비밀번호 불일치"),
    ALREADY_VERIFIED(401,"AC03", "이미 인증된 토큰"),
    DUPLICATED_STUDENT_ID(401,"AC04", "학번 중복"),
    DUPLICATED_EMAIL(401,"AC05", "이메일 중복"),
    DUPLICATED_ID(401,"AC06", "아이디 중복"),
    SIGNIN_REQUIRED(401, "AC07", "로그인 필요")
    ;

    private int status;
    private final String code;
    private final String message;

    AccountErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
