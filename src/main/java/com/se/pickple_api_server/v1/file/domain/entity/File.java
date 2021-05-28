package com.se.pickple_api_server.v1.file.domain.entity;

import com.se.pickple_api_server.v1.board.domain.entity.Board;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_id", referencedColumnName = "boardId")
    private Board boardId;

    @Column(length = 255)
    @Size(min = 2, max = 255)
    private String downloadUrl;

    @Column(length = 255)
    @Size(min = 2, max = 255)
    private String fileName;


    public File(@Size(min = 2, max = 255) String downloadUrl, @Size(min = 2, max = 255) String fileName) {
        this.downloadUrl = downloadUrl;
        this.fileName = fileName;
    }


}
