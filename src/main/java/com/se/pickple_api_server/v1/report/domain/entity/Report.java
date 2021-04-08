package com.se.pickple_api_server.v1.report.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="board_id", referencedColumnName = "boardId")
    private Board boardId;

    @Size(min=2, max=255)
    @Column(nullable = false)
    private String text;

    @Column(nullable = false, columnDefinition = "varchar(20) default 'BEFORE'")
    @Enumerated(value = EnumType.STRING)
    private ReportState reportState = ReportState.BEFORE;

    @Column(nullable = false, columnDefinition = "varchar(20) default 'NONE'")
    @Enumerated(value = EnumType.STRING)
    private ReportResult reportResult = ReportResult.NONE;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name="manager", referencedColumnName = "accountId")
    private Account manager;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name="reporter", referencedColumnName = "accountId")
    private Account reporter;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name="reported", referencedColumnName = "accountId")
    private Account reported;

    @Builder
    public Report(Long reportId, Board boardId, @Size(min = 2, max = 255) String text, ReportState reportState, ReportResult reportResult, Account manager, Account reporter, Account reported) {
        this.reportId = reportId;
        this.boardId = boardId;
        this.text = text;
        this.reportState = reportState;
        this.reportResult = reportResult;
        this.manager = manager;
        this.reporter = reporter;
        this.reported = reported;
    }
}
