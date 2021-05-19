package com.se.pickple_api_server.v1.report.application.error;

import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import lombok.Getter;

@Getter
public enum ReportErrorCode implements ErrorCode {

    NO_SUCH_REPORT(400, "RP01", "존재하지 않는 신고글"),
    INVALID_INPUT(401, "RP02", "입력값이 올바르지 않음");

    private int status;
    private final String code;
    private final String message;

    ReportErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}