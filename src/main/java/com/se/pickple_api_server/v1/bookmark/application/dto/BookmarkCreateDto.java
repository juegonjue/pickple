package com.se.pickple_api_server.v1.bookmark.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class BookmarkCreateDto {

    @ApiModel("북마크 등록 요청")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Request {
        @ApiModelProperty(notes = "모집글 아이디", example = "1")
        @NotNull
        private Long boardId;
    }
}
