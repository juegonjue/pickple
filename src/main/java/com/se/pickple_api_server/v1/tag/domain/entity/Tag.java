package com.se.pickple_api_server.v1.tag.domain.entity;

import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Size(min = 2, max = 20)
    @Column(nullable = false, unique = true)
    private String tagName;

    @Builder
    public Tag(Long tagId, @Size(min = 2, max = 20) String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

}
