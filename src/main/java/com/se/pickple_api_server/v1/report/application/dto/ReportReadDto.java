package com.se.pickple_api_server.v1.report.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.se.pickple_api_server.v1.report.domain.entity.Report;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.*;

public class ReportReadDto {

    @ApiModel("신고 상세 조회")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Response {
        private Long reportId;
        private String reportText;

        private Long boardId;
        private String boardTitle;
        private String boardText;

        @JsonInclude(Include.NON_NULL)
        private Long managerId;

        @JsonInclude(Include.NON_NULL)
        private String managerString;

        private Long reporterId;
        private String reporterString;

        private Long reportedId;
        private String reportedString;

        private String reportState;
        private String reportResult;

        static public Response fromEntity(Report report) {
            ResponseBuilder builder = Response.builder();
            builder
                    .reportedId(report.getReportId())
                    .reportText(report.getText())
                    .boardId(report.getBoard().getBoardId())
                    .boardTitle(report.getBoard().getTitle())
                    .boardText(report.getBoard().getText());

            if (report.getManager() != null)
                builder
                        .managerId(report.getManager().getAccountId())
                        .managerString(report.getManager().getIdString());

            builder
                    .reporterId(report.getReporter().getAccountId())
                    .reporterString(report.getReporter().getIdString())
                    .reportedId(report.getReported().getAccountId())
                    .reportedString(report.getReported().getIdString())
                    .reportState(report.getReportState().toString())
                    .reportResult(report.getReportResult().toString());
            return builder.build();
        }

    }


    @ApiModel("전체 신고 목록 조회")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ListResponse {

        private Long reportId;
        private Long boardId;
        private String reportText;
        private String reporterString;
        private String reportState;
        private String reportResult;

        static public ListResponse fromEntity(Report report) {
            ListResponseBuilder builder = ListResponse.builder();
            builder
                    .reportId(report.getReportId())
                    .boardId(report.getBoard().getBoardId())
                    .reportText(report.getText())
                    .reporterString(report.getReporter().getIdString())
                    .reportState(report.getReportState().toString())
                    .reportResult(report.getReportResult().toString());

            return builder.build();
        }
    }


    @ApiModel("내가 한 신고 목록 조회")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class MyResponse {
        private Long reportId;
        private Long boardId;
        private Integer boardIsDeleted;
        private String reportText;
        private String reportState;
        private String reportResult;

        static public MyResponse fromEntity(Report report) {
            MyResponseBuilder builder = MyResponse.builder();
            builder
                    .reportId(report.getReportId())
                    .boardId(report.getBoard().getBoardId())
                    .boardIsDeleted(report.getBoard().getIsDeleted())
                    .reportText(report.getText())
                    .reportState(report.getReportState().toString())
                    .reportResult(report.getReportResult().toString());
            return builder.build();
        }
    }

}
