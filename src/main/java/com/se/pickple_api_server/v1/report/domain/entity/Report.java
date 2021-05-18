package com.se.pickple_api_server.v1.report.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.report.domain.type.ReportResult;
import com.se.pickple_api_server.v1.report.domain.type.ReportState;
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
    private Board board;

    @Size(min=2, max=255)
    @Column(nullable = false)
    private String text;

    // 처리 상태
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReportState reportState = ReportState.BEFORE;

    // 처리 결과
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReportResult reportResult = ReportResult.NONE;

    // 담당자
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="manager", referencedColumnName = "accountId")
    private Account manager;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name="reporter", referencedColumnName = "accountId")
    private Account reporter;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name="reported", referencedColumnName = "accountId")
    private Account reported;


    public Report(Board board, @Size(min = 2, max = 255) String text, ReportState reportState, ReportResult reportResult, Account reporter, Account reported) {
        this.board = board;
        this.text = text;
        this.reportState = reportState;
        this.reportResult = reportResult;
        this.reporter = reporter;
        this.reported = reported;
    }

    // 신고 상태 업데이트 -> manager, reportState
    //public updateReportState()

    // 신고 처리되었을 때 -> reportState, reportResult
}
