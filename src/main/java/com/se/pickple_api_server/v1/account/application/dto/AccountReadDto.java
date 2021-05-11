package com.se.pickple_api_server.v1.account.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.domain.entity.AccountType;
import com.se.pickple_api_server.v1.account.domain.entity.RegisterType;
import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class AccountReadDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class Response {
        private String idString;
        private String name;
        private String email;
        private AccountType accountType;
        private Integer isDeleted;

        @JsonInclude(Include.NON_NULL)
        private Long accountId;

        @JsonInclude(Include.NON_NULL)
        private String studentId;

        @JsonInclude(Include.NON_NULL)
        private Integer isCertified;

        @JsonInclude(Include.NON_NULL)
        private RegisterType registerType;

        public static Response fromEntity(Account account) {
            return Response.builder()
                    .accountId(account.getAccountId())
                    .idString(account.getIdString())
                    .name(account.getName())
                    .accountType(account.getAccountType())
                    .email(account.getEmail())
                    .isCertified(account.getIsCertified())
                    .registerType(account.getRegisterType())
                    .isDeleted(account.getIsDeleted())
                    .build();
        }

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("회원 검색")
    static public class SearchRequest {

        @ApiModelProperty(notes = "검색할 키워드(회원아이디String, 회원이름)", example = "test")
        private String keyword;

        @ApiModelProperty(notes = "멤버 타입으로 찾기(추가사항)")
        private AccountType type;

        @NotNull
        private PageRequest pageRequest;
    }
}
