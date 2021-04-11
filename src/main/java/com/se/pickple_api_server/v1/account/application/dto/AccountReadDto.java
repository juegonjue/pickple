package com.se.pickple_api_server.v1.account.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.se.pickple_api_server.v1.account.domain.entity.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AccountReadDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class Request {
        private String name;
        private String nickname;
        private String email;
    }

    @Data
    @Builder
    static public class Response {
        private String idString;
        private String name;
        private String email;
        private AccountType accountType;

        @JsonInclude(Include.NON_NULL)
        private Long accountId;

        @JsonInclude(Include.NON_NULL)
        private String phoneNumber;

        @JsonInclude(Include.NON_NULL)
        private String studentId;

        @JsonInclude(Include.NON_NULL)
        private String isCertified;


    }
}
