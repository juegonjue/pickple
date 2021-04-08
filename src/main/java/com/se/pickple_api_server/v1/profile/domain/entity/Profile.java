package com.se.pickple_api_server.v1.profile.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    @Size(min = 2, max = 255)
    @Column(nullable = false, unique = true)
    private String blog;

    @Size(min = 2, max = 500)
    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer isOpen;

    //
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    @Builder
    public Profile(Long profileId, Account accountId, @Size(min = 10, max = 20) String workPhoneNumber, @Size(min = 2, max = 40) @Email String workEmail, @Size(min = 2, max = 255) String blog, @Size(min = 2, max = 500) String introduce, Integer isOpen, List<Tag> tags) {
        this.profileId = profileId;
        this.accountId = accountId;
        this.workPhoneNumber = workPhoneNumber;
        this.workEmail = workEmail;
        this.blog = blog;
        this.introduce = introduce;
        this.isOpen = isOpen;
        this.tags = tags;
    }
}
