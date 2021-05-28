package com.se.pickple_api_server.v1.recruitment.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import com.se.pickple_api_server.v1.board.domain.type.BoardType;
import lombok.AccessLevel;
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

    @OneToMany(mappedBy = "recruitmentBoard", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RecruitmentBoardTag> recruitmentBoardTagList = new ArrayList<>();

//    @Column
//    private String tagString;

    public RecruitmentBoard(Account writerId, @Size(min = 2, max = 50) String title, @Size(min = 2, max = 2000) String text, BoardType boardType, Integer hit, Integer isDeleted,
                            Integer recNumber, Integer paymentMin, Integer paymentMax, LocalDateTime workStartDate, LocalDateTime workEndDate, LocalDateTime recStartDate, LocalDateTime recEndDate, List<RecruitmentBoardTag> recruitmentBoardTagList) {
        super(writerId, title, text, boardType, hit, isDeleted);
        this.recNumber = recNumber;
        this.paymentMin = paymentMin;
        this.paymentMax = paymentMax;
        this.workStartDate = workStartDate;
        this.workEndDate = workEndDate;
        this.recStartDate = recStartDate;
        this.recEndDate = recEndDate;
        addTags(recruitmentBoardTagList);
        //this.tagString = tagsToString(this.recruitmentBoardTagList);
    }

    // 모집글_태그 등록에 필요
    public void addTags(List<RecruitmentBoardTag> recruitmentBoardTagList) {
        recruitmentBoardTagList
                .stream()
                .forEach(tag -> tag.setRecBoard(this));
    }

    // 모집글_태그 리스트에 원소 추가
    public void addTag(RecruitmentBoardTag recruitmentBoardTag) {
        this.recruitmentBoardTagList.add(recruitmentBoardTag);
    }

    public void updateRecContents(Integer recNumber, Integer paymentMax,
                                  LocalDateTime workStartDate, LocalDateTime workEndDate,
                                  LocalDateTime recStartDate, LocalDateTime recEndDate) {

        this.recNumber = recNumber;
        this.paymentMax = paymentMax;
        this.workStartDate = workStartDate;
        this.workEndDate = workEndDate;
        this.recStartDate = recStartDate;
        this.recEndDate = recEndDate;
    }

    public void updateTagContents(List<RecruitmentBoardTag> newRecruitmentBoardTagList) {
        this.recruitmentBoardTagList
                .forEach(recruitmentBoardTag -> recruitmentBoardTag.setRecBoard(null));
        this.recruitmentBoardTagList.clear();
        addTags(newRecruitmentBoardTagList);
    }

    public void update(String title, String text,
                       Integer recNumber, Integer paymentMax,
                       LocalDateTime workStartDate, LocalDateTime workEndDate,
                       LocalDateTime recStartDate, LocalDateTime recEndDate,
                       List<RecruitmentBoardTag> recruitmentBoardTagList) {
        updateBoardContents(title, text);
        updateRecContents(recNumber, paymentMax, workStartDate, workEndDate, recStartDate, recEndDate);
        updateTagContents(recruitmentBoardTagList);
    }

//    public String tagsToString(List<RecruitmentBoardTag> recruitmentBoardTagList) {
//        String tagStr = null;
//        for (RecruitmentBoardTag tag : recruitmentBoardTagList)
//            tagStr += tag.getTag().getTagName();
//        System.out.println(tagStr);
//        return tagStr;
//    }

}

