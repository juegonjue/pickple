package com.se.pickple_api_server.v1.apply.application.error;

import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import lombok.Getter;

@Getter
public enum ApplyErrorCode implements ErrorCode {

    NO_SUCH_APPLY(400, "AP01", "존재하지 않는 지원"),
    DUPLICATED_APPLY(401, "AP02", "이미 해당 게시물에 대한 지원 존재함"),
    INVALID_REVIEW_STATE(401, "AP03", "리뷰를 작성할 수 없는 상태");


    private int status;
    private final String code;
    private final String message;

    ApplyErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

