package com.se.pickple_api_server.domain.entity.board;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@DiscriminatorValue("F")
public class FreeBoard extends Board{

    @Size(min=2, max=20)
    @Column(columnDefinition = "varchar(20) default 'none'")
    private FreeBoardCategory category;

}
