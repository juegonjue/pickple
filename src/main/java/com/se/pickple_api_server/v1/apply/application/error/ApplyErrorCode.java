package com.se.pickple_api_server.v1.apply.application.error;

import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApplyErrorCode implements ErrorCode {

    NO_SUCH_APPLY(400, "AP01", "존재하지 않는 지원"),
    DUPLICATED_APPLY(401, "AP02", "이미 해당 게시물에 대한 지원 존재함"),
    STATE_NOT_WAITING(401, "AP03", "대기 상태가 아닌 후기는 변경 불가"),
    INVALID_REVIEW_STATE(401, "AP04", "유효하지 않은 리뷰상태"),
    CANNOT_WRITE_REVIEW(401, "AP05","계약되지 않았거나 승인된 후기는 (재)작성 불가");

    private int status;
    private final String code;
    private final String message;

    ApplyErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

