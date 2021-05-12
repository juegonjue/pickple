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
    private Long applyId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId")
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_id", referencedColumnName = "boardId")
    private Board board;

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


    public Apply(Profile profile, Board board, Integer isContracted, @Size(max = 255) String review, ReviewState reviewState, Integer isDeleted) {
        this.profile = profile;
        this.board = board;
        this.isContracted = isContracted;
        this.review = review;
        this.reviewState = reviewState;
        this.isDeleted = isDeleted;
    }

    public void updateIsContracted(Integer isContracted) { this.isContracted = isContracted; }

    public void updateReview(String review) { this.review = review; }

    public void updateReviewState(ReviewState reviewState) {this.reviewState = reviewState; }

    public void updateIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }
}
