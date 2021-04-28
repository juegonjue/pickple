//package com.se.pickple_api_server.v1.account.domain.entity;
//
//import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
//import lombok.AccessLevel;
//import lombok.*;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class AccountVerifyToken extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long accountVerifyUrl;
//
//    @Column(length = 40)
//    @Email
//    private String email;
//
//    @Column(length = 255, unique = true)
//    private String token;
//
//    private LocalDateTime timeExpire;
//
//    @Column(length = 30)
//    @Enumerated(EnumType.STRING)
//    private AccountVerifyStatus status;
//
//    @Builder
//    public AccountVerifyToken(Long accountVerifyUrl, String email, String token, LocalDateTime timeExpire, AccountVerifyStatus status) {
//        this.accountVerifyUrl = accountVerifyUrl;
//        this.email = email;
//        this.token = token;
//        this.timeExpire = timeExpire;
//        this.status = status;
//    }
//
//    public void verify() {
//        this.status = AccountVerifyStatus.VERIFIED;
//    }
//}
