package com.se.pickple_api_server.v1.apply.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApplyUpdateDto {

    // 후기 쓰기
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ReviewRequest {
        private Long applyId;

        @ApiModelProperty(notes = "작성할 리뷰", example = "이 친구 잘해요~")
        private String review;
    }

    // 후기 승인 / 반려
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ReviewStatusRequest {
         private String reviewState;
    }


}
