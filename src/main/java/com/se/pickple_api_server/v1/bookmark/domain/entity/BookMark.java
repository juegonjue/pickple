package com.se.pickple_api_server.v1.bookmark.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMark{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookMarkId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="account_id", referencedColumnName = "accountId")
    private Account accountId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="board_id", referencedColumnName = "boardId")
    private Board boardId;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean is_deleted;

    public BookMark(Long bookMarkId, Account accountId, Board boardId, Boolean is_deleted) {
        this.bookMarkId = bookMarkId;
        this.accountId = accountId;
        this.boardId = boardId;
        this.is_deleted = is_deleted;
    }
}
