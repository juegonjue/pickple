package com.se.pickple_api_server.v1.recboard_tag.domain.entity;

import com.se.pickple_api_server.v1.board.domain.entity.Board;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import lombok.AccessLevel;
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
    @JoinColumn(nullable = false, name = "board_id", referencedColumnName = "boardId")
    private Board boardId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "tag_id", referencedColumnName = "tagId")
    private Tag tagId;


    public RecruitmentBoardTag(Board boardId, Tag tagId) {
        this.boardId = boardId;
        this.tagId = tagId;
    }
}
