package com.se.pickple_api_server.v1.apply.application.dto;

import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
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

        static public MyResponse fromEntity(Apply apply) {
            MyResponseBuilder builder = MyResponse.builder();
            builder
                    .boardId(apply.getBoard().getBoardId())
                    .boardTitle(apply.getBoard().getTitle());
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
                    .boardId(apply.getBoard().getBoardId())
                    .boardTitle(apply.getBoard().getTitle())
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
}
