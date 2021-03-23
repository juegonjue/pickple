package com.se.pickple_api_server.domain.entity.bookmark;

import com.se.pickple_api_server.domain.entity.account.Account;
import com.se.pickple_api_server.domain.entity.board.Board;

import javax.persistence.*;

public class BookMark {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookMarkId;

    @ManyToOne()
    @JoinColumn(name="", referencedColumnName = "accountId")
    private Account accountId;

    @ManyToOne()
    @JoinColumn(name="board_id", referencedColumnName = "boairdId")
    private Board boardId;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean is_deleted;
}
