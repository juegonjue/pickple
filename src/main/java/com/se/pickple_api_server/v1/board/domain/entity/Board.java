package com.se.pickple_api_server.domain.entity.board;

import com.se.pickple_api_server.domain.entity.BaseEntity;
import com.se.pickple_api_server.domain.entity.account.Account;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BoardId;

    @ManyToOne(targetEntity = Account.class, fetch=FetchType.LAZY)
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

    @Column(columnDefinition = "int default 0")
    private Integer hit;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;


}
