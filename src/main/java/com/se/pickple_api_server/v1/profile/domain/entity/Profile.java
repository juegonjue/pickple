package com.se.pickple_api_server.v1.profile.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.profile_tag.domain.entity.ProfileTag;
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

    //@OneToOne(mappedBy = "profile")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "account_id" , referencedColumnName = "accountId")
    private Account account;

    @Size(min = 2, max = 20)
    @Column(unique = true)
    private String kakaoId; // null

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

    @Column(nullable = false)
    private Integer isOpen;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ProfileTag> profileTagList = new ArrayList<>();


    @Builder
    public Profile(Account account, @Size(min = 2, max = 20) String kakaoId, @Size(min = 2, max = 40) @Email String workEmail, @Size(max = 255) String blog, @Size(min = 2, max = 500) String introduce, Integer isOpen, List<ProfileTag> profileTagList) {
        this.account = account;
        this.kakaoId = kakaoId;
        this.workEmail = workEmail;
        this.blog = blog;
        this.introduce = introduce;
        this.isOpen = isOpen;
        addTags(profileTagList);
    }

    public void addTags(List<ProfileTag> profileTagList) {
        profileTagList
                .stream()
                .forEach(tag -> tag.setProfile(this));
    }

    public void addTag(ProfileTag profileTag) {
        this.profileTagList.add(profileTag);
    }
}
