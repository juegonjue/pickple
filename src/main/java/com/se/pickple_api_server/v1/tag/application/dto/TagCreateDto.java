package com.se.pickple_api_server.v1.tag.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TagCreateDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel("태그 등록 요청")
    static public class Request {
        @ApiModelProperty(example = "JAVA", notes = "태그")
        @Size(min = 1, max = 30)
        @NotEmpty
        private String tagName;
    }

    @Data
    @NoArgsConstructor
    @Builder
    static public class Response {

    }
}
