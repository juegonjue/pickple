package com.se.pickple_api_server.v1.profile.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "account_id" , referencedColumnName = "accountId")
    private Account accountId;

    @Size(min = 10, max = 20)
    @Column(nullable = false, unique = true)
    private String workPhoneNumber;

    @Size(min = 2, max = 40)
    @Column(nullable = false, unique = true)
    @Email
    private String workEmail;

    @Size(max = 255)
    @Column(unique = true)
    private String blog;

    @Size(min = 2, max = 500)
    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer isOpen;


    public Profile(Account accountId, @Size(min = 10, max = 20) String workPhoneNumber, @Size(min = 2, max = 40) @Email String workEmail, @Size(max = 255) String blog, @Size(min = 2, max = 500) String introduce, Integer isOpen) {
        this.accountId = accountId;
        this.workPhoneNumber = workPhoneNumber;
        this.workEmail = workEmail;
        this.blog = blog;
        this.introduce = introduce;
        this.isOpen = isOpen;
    }
}
