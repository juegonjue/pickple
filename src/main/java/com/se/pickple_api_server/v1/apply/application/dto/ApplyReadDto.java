package com.se.pickple_api_server.v1.apply.application.dto;

import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApplyReadDto {

    // 내가 제출한 모든 지원 조회 --> 보드 번호, 보드 제목
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class MyResponse {
        private Long boardId;
        private String boardTitle;
        private String boardRecEndDate;
        private String boardWordStartDate;

        static public MyResponse fromEntity(Apply apply) {
            MyResponseBuilder builder = MyResponse.builder();
            builder
                    .boardId(apply.getRecruitmentBoard().getBoardId())
                    .boardTitle(apply.getRecruitmentBoard().getTitle())
                    .boardRecEndDate(apply.getRecruitmentBoard().getRecEndDate().toString())
                    .boardWordStartDate(apply.getRecruitmentBoard().getWorkStartDate().toString());
            return builder.build();
        }
    }

    // 내 모집글에 들어온 모든 지원 조회 --> 보드 번호, 보드 제목, 프로필 아이디
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class MeResponse {
        private Long boardId;
        private String boardTitle;
        private Long profileId;

        static public MeResponse fromEntity(Apply apply) {
            MeResponseBuilder builder = MeResponse.builder();
            builder
                    .boardId(apply.getRecruitmentBoard().getBoardId())
                    .boardTitle(apply.getRecruitmentBoard().getTitle())
                    .profileId(apply.getProfile().getProfileId());
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
            ExistResponseBuilder builder = ExistResponse.builder();
            return builder.applyId(apply.getApplyId()).build();
        }
    }

    // 지원자 보러가기 --> 프로필의 아이디 + 프로필 자기소개 + 어카운트 이름

}
