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
    static public class Response {
        private Long boardId;
        private String boardTitle;

        static public Response fromEntity(Bookmark bookmark) {
            ResponseBuilder builder = Response.builder();

            builder
                    .boardId(bookmark.getBookmarkId())
                    .boardTitle(bookmark.getBoard().getTitle());

            return builder.build();
        }

    }

    // TODO 현재 페이지에서 내 북마크여부 조회 ?

}
