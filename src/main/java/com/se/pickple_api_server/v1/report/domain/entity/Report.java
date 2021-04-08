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

    @Size(min = 2, max = 20)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'before'")
    private String state;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="manager", referencedColumnName = "accountId")
    private Account manager;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="reporter", referencedColumnName = "accountId")
    private Account reporter;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="reported", referencedColumnName = "accountId")
    private Account reported;

    @Builder
    public Report(Long reportId, Board boardId, @Size(min = 2, max = 255) String text, @Size(min = 2, max = 20) String state, Account manager, Account reporter, Account reported) {
        this.reportId = reportId;
        this.boardId = boardId;
        this.text = text;
        this.state = state;
        this.manager = manager;
        this.reporter = reporter;
        this.reported = reported;
    }
}
