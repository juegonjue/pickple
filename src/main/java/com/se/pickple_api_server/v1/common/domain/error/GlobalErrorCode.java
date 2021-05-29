package com.se.pickple_api_server.v1.common.domain.error;

import lombok.Getter;

@Getter
public enum GlobalErrorCode implements ErrorCode {

  UNKNOWN_NON_BUSINESS_ERROR(400, "GE01", "처리되지 않은 에러"),
  INVALID_INPUT_VALUE(401, "GE02", "올바르지 않은 입력"),
  METHOD_NOT_ALLOWED(401, "GE03", "올바르지 않은 입력"),
  HANDLE_ACCESS_DENIED(403, "GE04", "권한 없음");


  private final String code;
  private final String message;
  private int status;

  GlobalErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

}