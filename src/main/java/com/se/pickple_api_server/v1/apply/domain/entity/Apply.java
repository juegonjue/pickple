package com.se.pickple_api_server.v1.apply.domain.entity;

import com.se.pickple_api_server.v1.apply.domain.type.ReviewState;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId")
    private Profile profile;

    //@JoinColumn(name = "recruitment_board_id", referencedColumnName = "recruitmentBoardId")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn(name = "board_id", referencedColumnName = "boardId")
    private RecruitmentBoard recruitmentBoard;

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


    public Apply(Profile profile, RecruitmentBoard recruitmentBoard, Integer isContracted, ReviewState reviewState, Integer isDeleted) {
        this.profile = profile;
        this.recruitmentBoard = recruitmentBoard;
        this.isContracted = isContracted;
        this.reviewState = reviewState;
        this.isDeleted = isDeleted;
    }

    public void updateIsContracted(Integer isContracted) { this.isContracted = isContracted; }

    public void updateReview(String review) { this.review = review; }

    public void updateReviewState(ReviewState reviewState) {this.reviewState = reviewState; }

    public void updateIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }
}
