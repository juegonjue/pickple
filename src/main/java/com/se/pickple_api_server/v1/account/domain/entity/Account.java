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

    @Column(nullable = false, unique = true)
    private String idString;

    @Size(min = 2, max = 20)
    @Column(nullable = false)
    private String name;

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
    private Integer isDeleted;  // 0:존재, 1:삭제


    public void updateStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void updateIsDeleted(String idString, Integer isDeleted) {
        this.idString = idString;
        this.isDeleted = isDeleted;
    }

}