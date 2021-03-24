package com.se.pickple_api_server.v1.application.domain.entity;

import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="board_id", referencedColumnName = "boardId", nullable = false)
    private Board boardId;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "applier_id", referencedColumnName = "accountId", nullable = false)
    private Account applierId;

    @Size(min = 2, max = 50)
    @Column
    private String title;

    @Size(min = 2, max = 2000)
    @Column
    private String text;

    @Column(columnDefinition = "int default 0")
    private int hopePayment;

    @Column
    private LocalDateTime hopeStartDate;

    @Column
    private LocalDateTime hopeEndDate;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Builder
    public Application(Long applicationId, Board boardId, Account applierId, @Size(min = 2, max = 50) String title,
                     @Size(min = 2, max = 2000) String text, int hopePayment, LocalDateTime hopeStartDate,
                     LocalDateTime hopeEndDate, Boolean isDeleted) {
        this.applicationId = applicationId;
        this.boardId = boardId;
        this.applierId = applierId;
        this.title = title;
        this.text = text;
        this.hopePayment = hopePayment;
        this.hopeStartDate = hopeStartDate;
        this.hopeEndDate = hopeEndDate;
        this.isDeleted = isDeleted;
    }
}
