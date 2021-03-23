package com.se.pickple_api_server.v1.review.domain.entity;

import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="board_id",referencedColumnName = "boardId")
    private Board boardId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="writer_id", referencedColumnName = "accountId")
    private Account writerId;

    @Size(min = 2, max = 500)
    @Column(nullable = false)
    private String text;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public Review(Long review_id, Board boardId, Account writerId,
                  @Size(min = 2, max = 500) String text, Boolean isDeleted) {
        this.review_id = review_id;
        this.boardId = boardId;
        this.writerId = writerId;
        this.text = text;
        this.isDeleted = isDeleted;
    }
}
