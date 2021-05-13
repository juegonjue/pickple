package com.se.pickple_api_server.v1.recruitment.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import com.se.pickple_api_server.v1.board.domain.type.BoardType;
import com.se.pickple_api_server.v1.recboard_tag.domain.entity.RecruitmentBoardTag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("R")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentBoard extends Board {

    @Column(nullable = false)
    private Integer recNumber;

    @Column(nullable = false)
    private Integer paymentMin;

    @Column(nullable = false)
    private Integer paymentMax;

    @Column(nullable = false)
    private LocalDateTime workStartDate;

    @Column(nullable = false)
    private LocalDateTime workEndDate;

    @Column(nullable = false)
    private LocalDateTime recStartDate;

    @Column(nullable = false)
    private LocalDateTime recEndDate;

    @OneToMany(mappedBy = "recruitmentBoard", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RecruitmentBoardTag> recruitmentBoardTagList = new ArrayList<>();


    @Builder
    public RecruitmentBoard(Account writerId, @Size(min = 2, max = 50) String title, @Size(min = 2, max = 2000) String text, BoardType boardType, Integer hit, Integer isDeleted, Integer recNumber, Integer paymentMin, Integer paymentMax, LocalDateTime workStartDate, LocalDateTime workEndDate, LocalDateTime recStartDate, LocalDateTime recEndDate, List<RecruitmentBoardTag> recruitmentBoardTagList) {
        super(writerId, title, text, boardType, hit, isDeleted);
        this.recNumber = recNumber;
        this.paymentMin = paymentMin;
        this.paymentMax = paymentMax;
        this.workStartDate = workStartDate;
        this.workEndDate = workEndDate;
        this.recStartDate = recStartDate;
        this.recEndDate = recEndDate;
        addTags(recruitmentBoardTagList);
    }

    // 등록에 필요
    public void addTags(List<RecruitmentBoardTag> recruitmentBoardTagList) {
        recruitmentBoardTagList
                .stream()
                .forEach(tag -> tag.setRecBoard(this));
    }

    public void addTag(RecruitmentBoardTag recruitmentBoardTag) {
        this.recruitmentBoardTagList.add(recruitmentBoardTag);
    }
}

