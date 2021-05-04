package com.se.pickple_api_server.v1.board.application.dto;

import com.se.pickple_api_server.v1.board.domain.entity.BoardType;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.recboard_tag.domain.entity.RecruitmentBoardTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

        private Integer recNumber;

        private Integer paymentMin;

        private Integer paymentMax;

        private LocalDateTime workStartDate;

        private LocalDateTime workEndDate;

        private LocalDateTime recStartDate;

        private LocalDateTime recEndDate;

        //private List<RecruitmentBoardTag> recruitmentBoardTagList;

        static public Response fromEntity(RecruitmentBoard recruitmentBoard) {
            return Response.builder()
                    .boardId(recruitmentBoard.getBoardId())
                    .writerId(recruitmentBoard.getWriterId().getAccountId())
                    .title(recruitmentBoard.getTitle())
                    .text(recruitmentBoard.getText())
                    .boardType(recruitmentBoard.getBoardType())
                    .isDeleted(recruitmentBoard.getIsDeleted())
                    .recNumber(recruitmentBoard.getRecNumber())
                    .paymentMax(recruitmentBoard.getPaymentMax())
                    .workStartDate(recruitmentBoard.getWorkStartDate())
                    .workEndDate(recruitmentBoard.getWorkEndDate())
                    .recStartDate(recruitmentBoard.getRecStartDate())
                    .recEndDate(recruitmentBoard.getRecEndDate())
                    //.recruitmentBoardTagList(recruitmentBoard.getRecruitmentBoardTagList())
                    .build();
        }

    }

    // 전체조회

    //static public class ListResponse
}
