package com.se.pickple_api_server.v1.board.domain.entity;

import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="account_id",referencedColumnName = "accountId",nullable = false)
    private Account writerId;

    @Size(min=2,max=50)
    @Column(nullable=false)
    private String title;

    @Size(min=2, max=2000)
    @Column(nullable=false)
    private String text;

    @Column(nullable=false)
    private BoardType type;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer hit;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer isDeleted;

    public Board(Long boardId, Account writerId, @Size(min = 2, max = 50) String title, @Size(min = 2, max = 2000) String text, BoardType type, Integer hit, Integer isDeleted) {
        this.boardId = boardId;
        this.writerId = writerId;
        this.title = title;
        this.text = text;
        this.type = type;
        this.hit = hit;
        this.isDeleted = isDeleted;
    }
}
