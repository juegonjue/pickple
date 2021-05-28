package com.se.pickple_api_server.v1.tag.application.dto;

import com.se.pickple_api_server.v1.profile.domain.entity.ProfileTag;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoardTag;
import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import io.swagger.annotations.ApiModel;
import lombok.*;

public class TagReadDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class Response {
        private Long tagId;
        private String tagName;
        private String createDate;

        public static Response fromEntity(Tag tag) {
            return Response.builder()
                    .tagId(tag.getTagId())
                    .tagName(tag.getTagName())
                    .createDate(tag.getCreatedDate().toString())
                    .build();
        }

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class TagDto {
        private Long tagId;
        private String tagName;

        static public TagDto fromEntity(RecruitmentBoardTag recruitmentBoardTag) {
            return TagDto.builder()
                    .tagId(recruitmentBoardTag.getTag().getTagId())
                    .tagName(recruitmentBoardTag.getTag().getTagName())
                    .build();
        }

        static public TagDto fromEntity(ProfileTag profileTag) {
            return TagDto.builder()
                    .tagId(profileTag.getTag().getTagId())
                    .tagName(profileTag.getTag().getTagName())
                    .build();
        }

    }

}
