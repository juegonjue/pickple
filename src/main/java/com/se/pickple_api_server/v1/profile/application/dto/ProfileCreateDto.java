package com.se.pickple_api_server.v1.profile.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

public class ProfileCreateDto {

    @ApiModel("프로필 등록 요청")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Request {

        @ApiModelProperty(notes = "프로필 작성자 (고유번호)", example = "1")
        private Account accountId;

        @ApiModelProperty(notes = "카카오 아이디",  example = "imkakao")
        @Size(min = 2, max = 20)
        private String kakaoId;

        @ApiModelProperty()
        @Size(min = 2, max = 20)
        private String workEmail;

        private String blog;

        private String introduce;

        private Integer isOpen;

        // TODO 모집글의 DTO 끌고와도 사용가능?
        @ApiModelProperty(notes = "태그리스트")
        @Singular("tagList")
        private List<ProfileReadDto.TagDto> tagList;
    }


    @ApiModel("프로필 작성자 불러오기")
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Account {
        @ApiModelProperty(notes = "프로필 작성자 고유번호",example = "1")
        private Long writerId;
    }
}
