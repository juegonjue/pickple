package com.se.pickple_api_server.domain.entity.review;

import com.se.pickple_api_server.domain.entity.BaseEntity;
import com.se.pickple_api_server.domain.entity.account.Account;
import com.se.pickple_api_server.domain.entity.board.Board;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    @ManyToOne(targetEntity = Board.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Board boardId;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Account writerId;

    @Size
    @Column(nullable = false)
    private String text;


}
