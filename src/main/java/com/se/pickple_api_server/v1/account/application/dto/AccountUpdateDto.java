package com.se.pickple_api_server.v1.account.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

public class AccountUpdateDto {

    @Data
    @ApiModel("회원 정보 수정 요청")
    @Builder
    static public class Request {

        @NotEmpty
        @ApiModelProperty(name="사용자 아이디")
        private String id;

        @ApiModelProperty(name="변경할 학번", example = "20100000")
        private String newStudentId;
    }
}
