package com.se.pickple_api_server.v1.account.application.dto;

import com.se.pickple_api_server.v1.account.domain.entity.AccountType;
import com.se.pickple_api_server.v1.account.domain.entity.RegisterType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.*;

import javax.validation.constraints.Size;

public class AccountCreateDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("회원정보 등록 요청")
    static public class Request {

        @ApiModelProperty(example = "idString", notes = "아이디")
        private String idString;    // id -> idString 시

        @ApiModelProperty(example = "홍길동", notes = "이름")
        @Size(min = 2, max = 20)
        private String name;

        @ApiModelProperty(example = "MEMBER", notes = "사용자 타입")
        private AccountType accountType;

        @ApiModelProperty(example = "test@naver.com", notes = "이메일")
        @Size(min = 4, max = 40)
        private String email;

        @ApiModelProperty(example = "0", notes = "금오공대생 인증 여부")
        private Integer isCertified;

        @ApiModelProperty(example = "PICKPLE", notes = "사용자 유입 경로")
        private RegisterType registerType;

        @ApiModelProperty(example = "0", notes = "사용자 탈퇴 여부")
        private Integer isDeleted;

    }

    @Data
    @AllArgsConstructor
    @ApiModel("회원가입 응답")
    static public class Response {
        @ApiModelProperty(example = "1", notes = "사용자 pk")
        private Long id;
    }

}
