package com.se.pickple_api_server.v1.recruitment.application.dto;

import com.se.pickple_api_server.v1.board.domain.type.BoardType;
import com.se.pickple_api_server.v1.profile.domain.entity.ProfileTag;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoardTag;
import com.se.pickple_api_server.v1.tag.application.dto.TagReadDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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

        private String writerIdString;
        private String writerName;
        private Integer writerIsDeleted;

        private String title;

        private String text;

        private BoardType boardType;

        private Integer isDeleted;

        private Integer recNumber;

        private Integer paymentMax;

        private String workStartDate;

        private String workEndDate;

        private String recStartDate;

        private String recEndDate;

        private String createDate;
        private String updateDate;

        private List<TagReadDto.TagDto> recruitmentBoardTagList;

        static public Response fromEntity(RecruitmentBoard recruitmentBoard, Boolean isAdmin) {

            ResponseBuilder builder = Response.builder();

            builder
                    .boardId(recruitmentBoard.getBoardId())
                    .writerId(recruitmentBoard.getAccount().getAccountId())
                    .writerIdString(recruitmentBoard.getAccount().getIdString())
                    .writerName(recruitmentBoard.getAccount().getName())
                    .writerIsDeleted(recruitmentBoard.getAccount().getIsDeleted())
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

            if (isAdmin) {
                builder
                        .createDate(recruitmentBoard.getCreatedDate().toString())
                        .updateDate(recruitmentBoard.getModifiedDate().toString());
            }
            // 모집글의 태그리스트 build
            builder.recruitmentBoardTagList(
                    recruitmentBoard.getRecruitmentBoardTagList()
                            .stream()
                            .map(tag -> TagReadDto.TagDto.fromEntity(tag))
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

        private Integer recNumber;

        private Integer paymentMax;

        private String recStartDate;

        private String recEndDate;

        private List<TagReadDto.TagDto> recruitmentBoardTagList;

        static public ListResponse fromEntity(RecruitmentBoard recruitmentBoard) {
            ListResponseBuilder builder = ListResponse.builder();

            builder
                    .boardId(recruitmentBoard.getBoardId())
                    .writerId(recruitmentBoard.getAccount().getAccountId())
                    .idString(recruitmentBoard.getAccount().getIdString())
                    .title(recruitmentBoard.getTitle())
                    .text(recruitmentBoard.getText())
                    .recNumber(recruitmentBoard.getRecNumber())
                    .paymentMax(recruitmentBoard.getPaymentMax())
                    .recStartDate(recruitmentBoard.getRecStartDate().toString())
                    .recEndDate(recruitmentBoard.getRecEndDate().toString());

            // 태그리스트 build
            builder.recruitmentBoardTagList(
                    recruitmentBoard.getRecruitmentBoardTagList()
                            .stream()
                            .map(tag -> TagReadDto.TagDto.fromEntity(tag))
                            .collect(Collectors.toList())
            );

            return builder.build();
        }

    }

    // 내가 쓴 모집글 조회 (보드아이디, 제목, 모집 마감일, 업무 시작일)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class MyResponse {
        private Long boardId;
        private String title;
        private String recEndDate;
        private String workStartDate;
        private Integer isDeleted;

        static public MyResponse fromEntity(RecruitmentBoard recruitmentBoard) {
            MyResponseBuilder builder = MyResponse.builder();

            builder
                    .boardId(recruitmentBoard.getBoardId())
                    .title(recruitmentBoard.getTitle())
                    .recEndDate(recruitmentBoard.getRecEndDate().toString())
                    .workStartDate(recruitmentBoard.getWorkStartDate().toString())
                    .isDeleted(recruitmentBoard.getIsDeleted());

            return builder.build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class SListResponse {
        private Long boardId;
        private String title;
        private String text;

        static public SListResponse fromEntity(RecruitmentBoard recruitmentBoard) {
            return SListResponse.builder()
                    .boardId(recruitmentBoard.getBoardId())
                    .title(recruitmentBoard.getTitle())
                    .text(recruitmentBoard.getText())
                    .build();
        }
    }
}
