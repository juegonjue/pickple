package com.se.pickple_api_server.v1.apply.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApplyUpdateStatusDto {

    // 후기 승인 / 반려
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ReviewStatusRequest {
         private String reviewState;
    }
}
