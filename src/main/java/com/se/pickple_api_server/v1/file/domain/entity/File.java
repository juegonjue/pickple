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

    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String savedFileName;

    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String originFileName;

    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private Integer size;

    @Size(min = 1, max = 20)
    private String extension;

    public File(Long fileId, Board boardId,
                @Size(min = 3, max = 255) String savedFileName, @Size(min = 3, max = 255) String originFileName,
                @Size(min = 3, max = 255) String path, Integer size, @Size(min = 1, max = 20) String extension) {
        this.fileId = fileId;
        this.boardId = boardId;
        this.savedFileName = savedFileName;
        this.originFileName = originFileName;
        this.path = path;
        this.size = size;
        this.extension = extension;
    }
}
