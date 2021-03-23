package com.se.pickple_api_server.v1.board.domain.entity;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@DiscriminatorValue("F")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoard extends Board {

    @Size(min=2, max=20)
    @Column(columnDefinition = "varchar(20) default 'NONE'")
    private FreeBoardCategory category;

    @Builder
    public FreeBoard(Long boardId, Account writerId, @Size(min = 2, max = 50) String title,
                     @Size(min = 2, max = 2000) String text, BoardType type, Integer hit,
                     boolean isDeleted, @Size(min = 2, max = 20) FreeBoardCategory category) {
        super(boardId, writerId, title, text, type, hit, isDeleted);
        this.category = category;
    }
}
