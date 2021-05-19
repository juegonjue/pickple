package com.se.pickple_api_server.v1.recruitment.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class RecruitmentBoardUpdateDto {

    @ApiModel("모집글 수정 요청")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Request {

        @ApiModelProperty(name="보드 아이디")
        private Long boardId;

        @ApiModelProperty(notes = "변경할 제목", example = "변경된 제목 ")
        @Size(min = 2, max = 50)
        private String newTitle;

        @ApiModelProperty(notes = "변경할 내용", example = "변경된 내용")
        @Size(min = 2, max = 2000)
        private String newText;

        @ApiModelProperty(notes = "변경할 모집인원", example = "5")
        private Integer newRecNumber;

        @ApiModelProperty(notes = "변경할 금액", example = "100000")
        private Integer newPaymentMax;

        @ApiModelProperty(notes = "변경할 업무 시작일", example = "2020-02-02T00:00:00")
        private LocalDateTime newWorkStartDate;

        @ApiModelProperty(notes = "변경할 업무 종료일", example = "2020-02-02T00:00:00")
        private LocalDateTime newWorkEndDate;

        @ApiModelProperty(notes = "변경할 모집 시작일", example = "2020-02-02T00:00:00")
        private LocalDateTime newRecStartDate;

        @ApiModelProperty(notes = "변경할 모집 마감일", example = "2020-02-02T00:00:00")
        private LocalDateTime newRecEndDate;

        @ApiModelProperty(notes = "변경할 태그리스트")
        @Singular("tagList")
        private List<RecruitmentBoardCreateDto.TagDto> tagList;
    }

//    @ApiModel("모집글 태그 등록")
//    @Getter
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    static public class TagDto {
//        @ApiModelProperty(notes = "태그 번호", example = "1")
//        private Long tagId;
//    }
}
