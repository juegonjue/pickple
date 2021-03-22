package com.se.pickple_api_server.domain.entity.board;

import lombok.Getter;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@DiscriminatorValue("R")
public class RecruitmentBoard extends Board{

    @Column(columnDefinition = "int default 0")
    private int recNumber;

    @Column(columnDefinition = "int default 0")
    private int payment;

    @Column(columnDefinition = "datetime default now()")
    private LocalDateTime recStartDate;

    @Column
    private LocalDateTime recEndDate;

}
