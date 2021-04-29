package com.se.pickple_api_server.v1.profile_tag.domain.entity;

import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileTag extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileTagId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "profile_id" , referencedColumnName = "profileId")
    private Profile profileId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "tag_id" , referencedColumnName = "tagId")
    private Tag tagId;

    public ProfileTag(Profile profileId, Tag tagId) {
        this.profileId = profileId;
        this.tagId = tagId;
    }
}
