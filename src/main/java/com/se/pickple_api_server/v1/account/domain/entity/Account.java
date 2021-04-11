package com.se.pickple_api_server.v1.account.domain.entity;

import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class  Account extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Size(min = 4, max = 20)
    @Column(nullable = false, unique = true)
    private String idString;

    @Column(nullable = false)
    private String password;

    @Size(min = 2, max = 20)
    @Column(nullable = false)
    private String name;

    @Size(min = 2, max = 20)
    @Column(nullable = false, unique = true)
    private String nickname;

    @Size(min = 8, max = 20)
    @Column
    private String studentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Size(min = 10, max = 20)
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Size(min = 4, max = 40)
    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer isCertified;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RegisterType registerType;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer isDeleted;

    @Builder
    public Account(Long accountId, @Size(min = 5, max = 20) String idString, String password, @Size(min = 2, max = 20) String name, @Size(min = 2, max = 20) String nickname, @Size(min = 8, max = 20) String studentId, AccountType accountType, @Size(min = 10, max = 20) String phoneNumber, @Size(min = 4, max = 40) @Email String email, Integer isCertified, RegisterType registerType, Integer isDeleted) {
        this.accountId = accountId;
        this.idString = idString;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.studentId = studentId;
        this.accountType = accountType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isCertified = isCertified;
        this.registerType = registerType;
        this.isDeleted = isDeleted;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void changePassword(String newHashedPassword) {
        this.password = newHashedPassword;
    }

}