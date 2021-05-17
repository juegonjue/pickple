package com.se.pickple_api_server.v1.apply.application.dto;

import lombok.Data;

public class ApplyDeleteDto {

    @Data
    static public class Request {
        private Long applyId;
    }
}
