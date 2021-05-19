package com.se.pickple_api_server.v1.report.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReportUpdateDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Request {
        private Long reportId;

        private String manageString;

        @ApiModelProperty(notes = "신고 처리 결과 : NONE(default, 문제없음) / BOARD_DELETED / BOARD_MODIFIED / ACCOUNT_DELETED / GIVE_WARNING 존재함. 상태 바꿔준 후 관리자가 이대로 처리해야함", example = "BOARD_DELETED")
        private String reportResult;
    }

}
