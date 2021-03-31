package com.se.pickple_api_server.v1.account.domain.entity;

import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class  Account extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Size(min = 5, max = 20)
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
    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Size(min = 10, max = 20)
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Size(min = 4, max = 40)
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private Integer isCertified;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RegisterType registerType;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer isDeleted;

    @Builder
    //constructor, 만들고, 명세에서 default값으로 뭐가 들어가는지 확실히 명세 !


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