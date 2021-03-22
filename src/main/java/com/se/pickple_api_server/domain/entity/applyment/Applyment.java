package com.se.pickple_api_server.domain.entity.applyment;

import com.se.pickple_api_server.domain.entity.BaseEntity;
import com.se.pickple_api_server.domain.entity.account.Account;
import com.se.pickple_api_server.domain.entity.board.Board;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
public class Applyment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applymentId;

    @Column
    @ManyToOne(targetEntity = Board.class, fetch = FetchType.LAZY)
    private Board boardId;

    @Column
    @ManyToOne(targetEntity = Account.class, fetch=FetchType.LAZY)
    @JoinColumn("account_id")
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

    public Applyment(Long applymentId, @Size(min = 2, max = 50) String title, @Size(min = 2, max = 2000) String text, int hopePayment, LocalDateTime hopeStartDate, LocalDateTime hopeEndDate, Boolean isDeleted) {
        this.applymentId = applymentId;
        this.title = title;
        this.text = text;
        this.hopePayment = hopePayment;
        this.hopeStartDate = hopeStartDate;
        this.hopeEndDate = hopeEndDate;
        this.isDeleted = isDeleted;
    }

    protected Applyment() { }
}
