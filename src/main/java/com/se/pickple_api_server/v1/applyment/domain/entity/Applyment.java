package com.se.pickple_api_server.domain.entity.applyment;

import com.se.pickple_api_server.domain.entity.BaseEntity;
import com.se.pickple_api_server.domain.entity.account.Account;
import com.se.pickple_api_server.domain.entity.board.Board;
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
public class Applyment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applymentId;

    @Column
    @ManyToOne(targetEntity = Board.class, fetch = FetchType.LAZY)
    private Board boardId;

    @ManyToOne(targetEntity = Account.class, fetch=FetchType.LAZY)
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

}
