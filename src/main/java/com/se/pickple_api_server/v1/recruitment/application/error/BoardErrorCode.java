package com.se.pickple_api_server.v1.recruitment.application.error;

import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import lombok.Getter;

@Getter
public enum BoardErrorCode implements ErrorCode {
    NO_SUCH_BOARD(400, "PO01", "존재하지 않는 게시글"),
    INVALID_INPUT(401, "PO02", "입력값이 올바르지 않음");

    private int status;
    private final String code;
    private final String message;

    BoardErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}