package com.se.pickple_api_server.domain.entity.account;

import com.se.pickple_api_server.domain.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Size(min = 5, max = 20)
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Size(min = 2, max = 20)
    @Column(nullable = false)
    private String name;

    @Size(min = 2, max = 20)
    @Column(nullable = false)
    private String nickname;

    @Size(min = 8, max = 20)
    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private AccountType type;

    @Size(min = 10, max = 20)
    @Column
    private String phoneNumber;

    @Size(min = 4, max = 40)
    @Column(nullable = false)
    private String email;

    // ---
    @Column
    private Boolean isCertified;

    @Size(min = 2, max = 20)
    @Column
    private String membersOf;
    //---

}