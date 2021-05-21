package com.se.pickple_api_server.v1.profile.application.dto;

import com.se.pickple_api_server.v1.tag.application.dto.TagCreateDto;
import io.swagger.annotations.Api;
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

        @ApiModelProperty(notes = "카카오 아이디",  example = "imkakao")
        @Size(min = 2, max = 20)
        private String kakaoId;

        @ApiModelProperty(notes = "업무용 이메일", example = "test1@gmail.com")
        @Size(min = 2, max = 20)
        private String workEmail;

        @ApiModelProperty(notes = "블로그 주소", example = "github.com/test1")
        @Size(max = 255)
        private String blog;

        @ApiModelProperty(notes = "자기소개", example = "자기소개 글입니다.")
        @Size(min = 2, max = 500)
        private String introduce;

        private Integer isOpen;

        @ApiModelProperty(notes = "태그리스트")
        @Singular("tagList")
        private List<TagCreateDto.TagDto> tagList;
    }

//    @ApiModel("프로필 태그 등록")
//    @Getter
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    static public class TagDto {
//        @ApiModelProperty(notes = "태그 번호", example = "1")
//        private Long tagId;
//    }

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
