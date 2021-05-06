package com.se.pickple_api_server.v1.board.application.dto;

import com.se.pickple_api_server.v1.board.domain.entity.BoardType;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.recboard_tag.domain.entity.RecruitmentBoardTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class RecruitmentBoardReadDto {

    // 상세조회, 모든 내용 다 포함
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Response {

        private Long boardId;

        private Long writerId;

        private String idString;

        private String title;

        private String text;

        private BoardType boardType;

        private Integer isDeleted;

        private Integer recNumber;

//        private Integer paymentMin;

        private Integer paymentMax;

        private String workStartDate;

        private String workEndDate;

        private String recStartDate;

        private String recEndDate;

        private List<TagDto> recruitmentBoardTagList;

        static public Response fromEntity(RecruitmentBoard recruitmentBoard) {

            ResponseBuilder builder = Response.builder();

            builder
                    .boardId(recruitmentBoard.getBoardId())
                    .writerId(recruitmentBoard.getWriterId().getAccountId())
                    .idString(recruitmentBoard.getWriterId().getIdString())
                    .title(recruitmentBoard.getTitle())
                    .text(recruitmentBoard.getText())
                    .boardType(recruitmentBoard.getBoardType())
                    .isDeleted(recruitmentBoard.getIsDeleted())
                    .recNumber(recruitmentBoard.getRecNumber())
                    .paymentMax(recruitmentBoard.getPaymentMax())
                    .workStartDate(recruitmentBoard.getWorkStartDate().toString())
                    .workEndDate(recruitmentBoard.getWorkEndDate().toString())
                    .recStartDate(recruitmentBoard.getRecStartDate().toString())
                    .recEndDate(recruitmentBoard.getRecEndDate().toString());

            // 모집글의 태그리스트 build
            builder.recruitmentBoardTagList(
                    recruitmentBoard.getRecruitmentBoardTagList()
                            .stream()
                            .map(tag -> TagDto.fromEntity(tag))
                            .collect(Collectors.toList())
            );

            return builder.build();
        }

    }

    // 전체조회
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ListResponse {

        private Long boardId;

        private Long writerId;

        private String idString;

        private String title;

        private String text;

        private Integer isDeleted;

        private Integer recNumber;

//        private Integer paymentMin;

        private Integer paymentMax;

//        private String workStartDate;

//        private String workEndDate;

        private String recStartDate;

        private String recEndDate;

        private List<TagDto> recruitmentBoardTagList;

        static public ListResponse fromEntity(RecruitmentBoard recruitmentBoard) {
            ListResponseBuilder builder = ListResponse.builder();

            builder
                    .boardId(recruitmentBoard.getBoardId())
                    .writerId(recruitmentBoard.getWriterId().getAccountId())
                    .idString(recruitmentBoard.getWriterId().getIdString())
                    .title(recruitmentBoard.getTitle())
                    .text(recruitmentBoard.getText())
                    .isDeleted(recruitmentBoard.getIsDeleted())
                    .recNumber(recruitmentBoard.getRecNumber())
                    .paymentMax(recruitmentBoard.getPaymentMax())
                    .recStartDate(recruitmentBoard.getRecStartDate().toString())
                    .recEndDate(recruitmentBoard.getRecEndDate().toString());

            // 태그리스트 build
            builder.recruitmentBoardTagList(
                    recruitmentBoard.getRecruitmentBoardTagList()
                            .stream()
                            .map(tag -> TagDto.fromEntity(tag))
                            .collect(Collectors.toList())
            );

            return builder.build();
        }

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class TagDto {
        //private Long tagId;
        private String tagName;

        static public TagDto fromEntity(RecruitmentBoardTag recruitmentBoardTag) {
            return TagDto.builder()
                    ///.tagId(recruitmentBoardTag.getTag().getTagId())
                    .tagName(recruitmentBoardTag.getTag().getTagName())
                    .build();
        }
    }
}
