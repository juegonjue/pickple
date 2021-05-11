package com.se.pickple_api_server.v1.bookmark.application.dto;

import com.se.pickple_api_server.v1.bookmark.domain.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;

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

    // TODO 현재 페이지에서 내 북마크여부 조회 ? -> 현재 페이지에서 회원의 아이디에 대한 북마크 아이디 돌려줘야함
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class PresentResponse {
        private Long bookmarkId;

        static public PresentResponse fromEntity(Bookmark bookmark) {
            PresentResponseBuilder builder = PresentResponse.builder();
            return builder.bookmarkId(bookmark.getBookmarkId()).build();
        }
    }
}
