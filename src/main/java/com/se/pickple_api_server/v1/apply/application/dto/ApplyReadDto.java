package com.se.pickple_api_server.v1.apply.application.dto;

import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class ApplyReadDto {

    // 모든 지원 목록 조회 (관리자) ->
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ListResponse {
        private Long applyId;
        private Long recruitmentBoardId;
        private Integer isContracted;
        private String review;
        private String reviewState;

        static public ListResponse fromEntity(Apply apply) {
            ListResponseBuilder builder = ListResponse.builder();
            builder
                    .applyId(apply.getApplyId())
                    .recruitmentBoardId(apply.getRecruitmentBoard().getBoardId())
                    .isContracted(apply.getIsContracted())
                    .review(apply.getReview())
                    .reviewState(apply.getReviewState().toString());
            return builder.build();
        }
    }

    // 해당 지원 상세조회 (관리자, 모집자(고용주))
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Response {
        private Long applyId;
        private Long profileId;
        private String applierName;
        private Long recruitmentBoardId;
        private Integer isContracted;
        private String review;
        private String reviewState;
        private Integer isDeleted;

        private String createDate;
        private String updateDate;

        static public Response fromEntity(Apply apply, Boolean isAdmin) {
            ResponseBuilder builder = Response.builder();
            builder
                    .applyId(apply.getApplyId())
                    .profileId(apply.getProfile().getProfileId())
                    .applierName(apply.getProfile().getAccount().getName())
                    .recruitmentBoardId(apply.getRecruitmentBoard().getBoardId())
                    .isContracted(apply.getIsContracted())
                    .review(apply.getReview())
                    .reviewState(apply.getReviewState().toString())
                    .isDeleted(apply.getIsDeleted());
            if (isAdmin) {
                builder
                        .createDate(apply.getCreatedDate().toString())
                        .updateDate(apply.getModifiedDate().toString());
            }
            return builder.build();
        }
    }

    // 내가 제출한 모든 지원 조회 --> 보드 번호, 보드 제목
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class MyResponse {
        private Long applyId;
        private Long boardId;
        private String boardTitle;
        private String boardRecEndDate;
        private String boardWordStartDate;
        private Integer boardIsDeleted;
        private Integer applyIsContracted;

        static public MyResponse fromEntity(Apply apply) {
            MyResponseBuilder builder = MyResponse.builder();
            builder
                    .applyId(apply.getApplyId())
                    .boardId(apply.getRecruitmentBoard().getBoardId())
                    .boardTitle(apply.getRecruitmentBoard().getTitle())
                    .boardRecEndDate(apply.getRecruitmentBoard().getRecEndDate().toString())
                    .boardWordStartDate(apply.getRecruitmentBoard().getWorkStartDate().toString())
                    .boardIsDeleted(apply.getRecruitmentBoard().getIsDeleted())
                    .applyIsContracted(apply.getIsContracted());
            return builder.build();
        }
    }

    // 해당 모집글에 들어온 모든 지원 조회 --> 지원자 이름, 프로필 소개 , 지원상태, 프로필아이디.
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class MeResponse {
        private Long applyId;
        private Long profileId;
        private String accountName;
        private String profileIntroduce;
        private Integer isContracted;
        private String review;
        private String reviewState;

        static public MeResponse fromEntity(Apply apply) {
            MeResponseBuilder builder = MeResponse.builder();
            builder
                    .applyId(apply.getApplyId())
                    .profileId(apply.getProfile().getProfileId())
                    .accountName(apply.getProfile().getAccount().getName())
                    .profileIntroduce(apply.getProfile().getIntroduce())
                    .isContracted(apply.getIsContracted())
                    .review(apply.getReview())
                    .reviewState(apply.getReviewState().toString());

            return builder.build();
        }
    }

    // 해당 모집글의 내 지원 조회 --> 지원 아이디 (해제 가능?)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ExistResponse {
        private Long applyId;

        static public ExistResponse fromEntity(Apply apply) {
            return ExistResponse.builder()
                    .applyId(apply.getApplyId()).build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ReviewResponse {
        private String reviewWriterName;
        private String review;

        static public ReviewResponse fromEntity(Apply apply) {
            return ReviewResponse.builder()
                    .reviewWriterName(apply.getRecruitmentBoard().getAccount().getName())
                    .review(apply.getReview())
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class SListResponse {
        private Long applyId;
        private String review;
        private String reviewState;
        private Integer isContracted;

        static public SListResponse fromEntity(Apply apply) {
            return SListResponse.builder()
                    .applyId(apply.getApplyId())
                    .review(apply.getReview())
                    .reviewState(apply.getReviewState().toString())
                    .isContracted(apply.getIsContracted())
                    .build();
        }

    }
}
