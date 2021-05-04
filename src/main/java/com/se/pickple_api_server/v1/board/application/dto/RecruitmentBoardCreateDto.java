package com.se.pickple_api_server.v1.board.application.dto;

import com.se.pickple_api_server.v1.board.domain.entity.BoardType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class RecruitmentBoardCreateDto {

    @ApiModel("모집글 등록 요청")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Request {

        @ApiModelProperty(notes = "작성자 (고유)번호", example = "1")
        private WriterDto writerId;

        @ApiModelProperty(notes = "모집글 제목", example = "제목")
        @Size(min = 2, max = 50)
        @NotNull
        private String title;

        @ApiModelProperty(notes = "모집글 내용", example = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        @Size(min = 2, max = 2000)
        @NotNull
        private String text;

        @ApiModelProperty(notes = "게시글 타입", example = "RECRUITMENT")
        @NotNull
        private BoardType boardType;

        @ApiModelProperty(notes = "모집 인원", example = "1")
        @NotNull
        private Integer recNumber;

        @ApiModelProperty(notes = "최대 금액", example = "100000")
        private Integer paymentMax;

        @ApiModelProperty(notes = "업무 시작일", example = "2020-01-01T00:00:00")
        private LocalDateTime workStartDate;

        @ApiModelProperty(notes = "업무 종료일", example = "2020-01-01T00:00:00")
        private LocalDateTime workEndDate;

        @ApiModelProperty(notes = "모집 종료일", example = "2020-01-01T00:00:00")
        private LocalDateTime recEndDate;

        @ApiModelProperty(notes = "태그리스트")
        @Singular("tagList")
        private List<TagDto> tagList;
    }

    @ApiModel("모집글 태그 등록")
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class TagDto {
        @ApiModelProperty(notes = "태그 번호", example = "1")
        private Long tagId;
    }

    @ApiModel("모집글 작성자 불러오기")
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class WriterDto{
        @ApiModelProperty(notes = "글작성자 고유번호",example = "1")
        private Long writerId;
    }
}
