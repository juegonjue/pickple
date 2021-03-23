package com.se.pickple_api_server.v1.board.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Getter
@DiscriminatorValue("R")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentBoard extends Board {

    @Column(nullable = false, columnDefinition = "int default 0")
    private int recNumber;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int payment;

    @Column(nullable = false, columnDefinition = "datetime default now()")
    private LocalDateTime recStartDate;

    @Column(nullable = false)
    private LocalDateTime recEndDate;

    public RecruitmentBoard(Long boardId, Account writerId, @Size(min = 2, max = 50) String title, @Size(min = 2, max = 2000) String text, BoardType type, Integer hit, boolean isDeleted, int recNumber, int payment, LocalDateTime recStartDate, LocalDateTime recEndDate) {
        super(boardId, writerId, title, text, type, hit, isDeleted);
        this.recNumber = recNumber;
        this.payment = payment;
        this.recStartDate = recStartDate;
        this.recEndDate = recEndDate;
    }
}

