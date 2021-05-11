package com.se.pickple_api_server.v1.account.application.dto;

import com.se.pickple_api_server.v1.account.domain.entity.AccountType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AccountUpdateDto {

    @ApiModel("회원 정보 수정 요청")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Request {

        //@NotEmpty
        @ApiModelProperty(name="사용자 아이디")
        private Long accountId;
//        private String idString;

        @Size(min = 8, max = 20)
        @ApiModelProperty(name="변경할 학번", example = "01234567")
        private String newStudentId;

        @Size(min = 4, max = 40)
        @Email
        @ApiModelProperty(name = "변경할 이메일", example = "test@gmail.com")
        private String newEmail;

        @NotEmpty
        @ApiModelProperty(name = "(관리자만 수정) 사용자 타입", example = "ADMIN")
        private String accountType;

    }
}
