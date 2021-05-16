package com.se.pickple_api_server.v1.report.application.dto;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

public class ReportCreateDto {

    @ApiModel("신고하기")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Request {

        @ApiModelProperty(notes = "신고할 게시글", example = "1")
        private Long boardId;

        @ApiModelProperty(notes = "신고 내용", example = "관계없는 광고글인 것 같아요 ")
        @Size(min = 2, max = 255)
        private String text;

        @ApiModelProperty(notes = "처리 담당자", example = "2")
        private Long manager;

        @ApiModelProperty(notes = "피신고자", example = "2")
        private Long reported;
    }
}
