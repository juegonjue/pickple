package com.se.pickple_api_server.v1.bookmark.application.dto;

import com.se.pickple_api_server.v1.bookmark.domain.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BookmarkReadDto {

    // 내 모든 북마크 조회 -> (보드 번호, 보드 제목)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class MyResponse {
        private Long boardId;
        private String boardTitle;

        static public MyResponse fromEntity(Bookmark bookmark) {
            MyResponseBuilder builder = MyResponse.builder();

            builder
                    .boardId(bookmark.getBoard().getBoardId())
                    .boardTitle(bookmark.getBoard().getTitle());

            return builder.build();
        }
    }


    // 해당 모집글의 내 북마크 조회
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ExistResponse {
        private Long bookmarkId;

        static public ExistResponse fromEntity(Bookmark bookmark) {
            ExistResponseBuilder builder = ExistResponse.builder();
            return builder.bookmarkId(bookmark.getBookmarkId()).build();
        }
    }
}
