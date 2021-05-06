package com.se.pickple_api_server.v1.recboard_tag.domain.entity;

import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentBoardTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruitmentBoardTagId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "board_id", referencedColumnName = "board_id")
    private RecruitmentBoard recruitmentBoard;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "tag_id", referencedColumnName = "tagId")
    private Tag tag;


    @Builder
    public RecruitmentBoardTag(Long recruitmentBoardTagId, RecruitmentBoard recruitmentBoard, Tag tag) {
        this.recruitmentBoardTagId = recruitmentBoardTagId;
        this.recruitmentBoard = recruitmentBoard;
        this.tag = tag;
    }

    public void setRecBoard(RecruitmentBoard recruitmentBoard) {
        this.recruitmentBoard = recruitmentBoard;
        if (!recruitmentBoard.getRecruitmentBoardTagList().contains(this))
            recruitmentBoard.addTag(this);
    }
}
