package com.se.pickple_api_server.v1.common.domain.error;

public interface ErrorCode {

  String getCode();

  String getMessage();

  int getStatus();

}
