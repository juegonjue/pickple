package com.se.pickple_api_server.v1.tag.application.dto;

import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import io.swagger.annotations.ApiModel;
import lombok.*;

public class TagReadDto {

    // 태그 아이디로 조회 요청
//    @Data
//    @NoArgsConstructor
//    @Builder
//    @AllArgsConstructor
//    @ApiModel("태그 아이디로 조회 요청")
//    static public class ReadByIdRequest {
//        private Long id;
//    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class Response {
        private Long tagId;
        private String tagName;

        public static Response fromEntity(Tag tag) {
            return Response.builder()
                    .tagId(tag.getTagId())
                    .tagName(tag.getTagName())
                    .build();
        }


    }

}
