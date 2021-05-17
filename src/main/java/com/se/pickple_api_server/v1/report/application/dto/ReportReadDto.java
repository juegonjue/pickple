package com.se.pickple_api_server.v1.report.application.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReportReadDto {


    @ApiModel("신고 상세 조회")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Response {
        private Long reportId;
        private Long boardId;
        private String boardTitle;
        private String boardText;
        private Long managerId;
        private String managerString;
        private Long reporterId;
        private String reporterString;
        private Long reportedId;
        private String reportedString;
        private String reportState;
        private String reportResult;
    }


//    @ApiModel("전체 신고 목록 조회")
//    @Data
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    static public class ListResponse{
//
//        private Long reportId;
//        private Long boardId;
//        private
//
//    }
}
