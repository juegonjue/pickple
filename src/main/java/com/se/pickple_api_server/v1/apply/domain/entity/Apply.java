package com.se.pickple_api_server.v1.apply.domain.entity;

import com.se.pickple_api_server.v1.board.domain.entity.Board;
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
public class Apply extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId")
    private Profile profileId;

    @ManyToOne
    @JoinColumn(name = "recruitment_board_id", referencedColumnName = "boardId")
    private Board boardId;

    @Column(nullable = false)
    private Integer isContracted;

    @Size(max = 255)
    @Column
    private String review;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewState reviewState;

    @Column(nullable = false)
    private Integer isDeleted;

    @Builder
    public Apply(Long reviewId, Profile profileId, Board boardId, Integer isContracted, @Size(max = 255) String review, ReviewState reviewState, Integer isDeleted) {
        this.reviewId = reviewId;
        this.profileId = profileId;
        this.boardId = boardId;
        this.isContracted = isContracted;
        this.review = review;
        this.reviewState = reviewState;
        this.isDeleted = isDeleted;
    }
}
