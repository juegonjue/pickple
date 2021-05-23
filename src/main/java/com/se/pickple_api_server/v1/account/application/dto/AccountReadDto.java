package com.se.pickple_api_server.v1.account.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.domain.type.AccountType;
import com.se.pickple_api_server.v1.account.domain.type.RegisterType;
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

        private Long accountId;

        private String idString;

        private String name;

        private String accountType;

        private String email;

        @JsonInclude(Include.NON_NULL)
        private String studentId;

        @JsonInclude(Include.NON_NULL)
        private String registerType;

        @JsonInclude(Include.NON_NULL)
        private Integer isCertified;

        private Integer isDeleted;


        public static Response fromEntity(Account account) {
            return Response.builder()
                    .accountId(account.getAccountId())
                    .idString(account.getIdString())
                    .name(account.getName())
                    .accountType(account.getAccountType().toString())
                    .email(account.getEmail())
                    .studentId(account.getStudentId())
                    .registerType(account.getRegisterType().toString())
                    .isCertified(account.getIsCertified())
                    .isDeleted(account.getIsDeleted())
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class SListResponse {
        private Long accountId;
        private String idString;
        private String name;
        private AccountType accountType;
        private RegisterType registerType;

        public static SListResponse fromEntity(Account account) {
            return SListResponse.builder()
                    .accountId(account.getAccountId())
                    .idString(account.getIdString())
                    .name(account.getName())
                    .accountType(account.getAccountType())
                    .registerType(account.getRegisterType())
                    .build();
        }
    }

}
