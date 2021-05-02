package com.se.pickple_api_server.v1.board.application.dto;

import com.se.pickple_api_server.v1.board.domain.entity.BoardType;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RecruitmentBoardReadDto {

    // 상세조회
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Response {

        private Long boardId;

        private Long writerId;

        private String title;

        private String text;

        private BoardType boardType;

        private Integer isDeleted;

        public static Response fromEntity(RecruitmentBoard recruitmentBoard) {
            return Response.builder()
                    .boardId(recruitmentBoard.getBoardId())
                    .writerId(recruitmentBoard.getWriterId().getAccountId())
                    .title(recruitmentBoard.getTitle())
                    .text(recruitmentBoard.getText())
                    .boardType(recruitmentBoard.getBoardType())
                    .isDeleted(recruitmentBoard.getIsDeleted())
                    .build();
        }

    }

    // 전체조회

}
