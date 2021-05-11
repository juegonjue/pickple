package com.se.pickple_api_server.v1.profile.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import com.se.pickple_api_server.v1.profile_tag.domain.entity.ProfileTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileReadDto {

    // 상세조회, 모든 내용
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Response {

        private Long profileId;

        private Long accountId;

        private String userName;

        @JsonInclude(Include.NON_NULL)
        private String kakaoId;

        private String workEmail;

        @JsonInclude(Include.NON_NULL)
        private String blog;

        private String introduce;

        private Integer isOpen;

        private List<TagDto> profileTagList;

        static public Response fromEntity (Profile profile) {
            ResponseBuilder builder = Response.builder();

            builder
                    .profileId(profile.getProfileId())
                    .accountId(profile.getAccount().getAccountId())
                    .userName(profile.getAccount().getName())
                    .kakaoId(profile.getKakaoId())
                    .workEmail(profile.getWorkEmail())
                    .blog(profile.getBlog())
                    .introduce(profile.getIntroduce())
                    .isOpen(profile.getIsOpen());

            // 프로필의 태그리스트 build
            builder.profileTagList(
                    profile.getProfileTagList()
                            .stream()
                            .map(tag -> TagDto.fromEntity(tag))
                            .collect(Collectors.toList())
            );

            return builder.build();
        }
    }

    // 목록조회, 일부 내용
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class ListResponse {

        private Long profileId;

        private Long accountId;

        private String userName;

        private String introduce;

        private List<TagDto> profileTagList;

        static public ListResponse fromEntity (Profile profile) {
            ListResponseBuilder builder = ListResponse.builder();

            builder
                    .profileId(profile.getProfileId())
                    .accountId(profile.getAccount().getAccountId())
                    .userName(profile.getAccount().getName())
                    .introduce(profile.getIntroduce());

            // 프로필의 태그리스트 build
            builder.profileTagList(
                    profile.getProfileTagList()
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
       private Long tagId;
       private String tagName;

       static public TagDto fromEntity (ProfileTag profileTag){
           return TagDto.builder()
                   .tagId(profileTag.getTag().getTagId())
                   .tagName(profileTag.getTag().getTagName())
                   .build();
        }
    }
}
