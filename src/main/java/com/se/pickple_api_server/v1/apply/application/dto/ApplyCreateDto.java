package com.se.pickple_api_server.v1.apply.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class ApplyCreateDto {


    @ApiModel("지원(프로필) 제출 요청")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Request {

        @ApiModelProperty(notes = "모집글 번호", example = "1")
        @NotNull
        private Long boardId;

    }
}
