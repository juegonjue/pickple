package com.se.pickple_api_server.v1.profile.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import com.se.pickple_api_server.v1.tag.application.dto.TagReadDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
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

        private List<TagReadDto.TagDto> profileTagList;

        private String createDate;
        private String updateDate;

        static public Response fromEntity(Profile profile, Boolean isAdmin) {
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
                            .map(tag -> TagReadDto.TagDto.fromEntity(tag))
                            .collect(Collectors.toList())
            );
            if (isAdmin) {
                builder
                        .createDate(profile.getCreatedDate().toString())
                        .updateDate(profile.getModifiedDate().toString());
            }
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

        private List<TagReadDto.TagDto> profileTagList;

        static public ListResponse fromEntity (Profile profile) {
            ListResponseBuilder builder = ListResponse.builder();

            builder
                    .profileId(profile.getProfileId())
                    .accountId(profile.getAccount().getAccountId())
                    .userName(profile.getAccount().getName())
                    .introduce(stringCutter(profile.getIntroduce(),100));

            // 프로필의 태그리스트 build
            builder.profileTagList(
                    profile.getProfileTagList()
                            .stream()
                            .map(tag -> TagReadDto.TagDto.fromEntity(tag))
                            .collect(Collectors.toList())
            );

            return builder.build();
        }
    }

    static private String stringCutter(String str, int length) {
        return str.length() < length ? str : str.substring(0,length) + " (...)";
    }

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

    // TODO 검색 프로필
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class SListResponse {
        private Long profileId;
        private String userName;
        private String introduce;

        static public SListResponse fromEntity(Profile profile) {
            return builder()
                    .profileId(profile.getProfileId())
                    .userName(profile.getAccount().getName())
                    .introduce(profile.getIntroduce())
                    .build();
        }

    }

}
