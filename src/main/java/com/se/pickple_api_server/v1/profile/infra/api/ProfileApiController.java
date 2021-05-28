package com.se.pickple_api_server.v1.profile.infra.api;


import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileCreateDto;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileReadDto;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileUpdateDto;
import com.se.pickple_api_server.v1.profile.application.service.ProfileCreateService;
import com.se.pickple_api_server.v1.profile.application.service.ProfileReadService;
import com.se.pickple_api_server.v1.profile.application.service.ProfileUpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "프로필 관리")
public class ProfileApiController {

    private final ProfileCreateService profileCreateService;
    private final ProfileReadService profileReadService;
    private final ProfileUpdateService profileUpdateService;

    @ApiOperation(value = "UC-PF-01 프로필 등록")
    @PostMapping(path = "/profile")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated ProfileCreateDto.Request request) {
        System.out.println("UC-PF-01 요청");
        return new SuccessResponse(HttpStatus.CREATED.value(), "프로필 등록 성공", profileCreateService.create(request));
    }

    // 프로필 전체조회
    @ApiOperation(value = "UC-PF-02 프로필 목록조회 페이징")
    @GetMapping(path = "/profile")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readAllProfile(@Validated PageRequest pageRequest) {
        System.out.println("UC-PF-02 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "프로필 목록 조회 페이징 성공", profileReadService.readAll(pageRequest.of()));
    }

    @ApiOperation(value = "UC-PF-03 [관리자] 프로필 조회 및 검색")
    @PostMapping(path = "/profile/search")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readSearchProfile(@RequestBody @Validated SearchDto.Request pageRequest) {
        System.out.println("UC-PF-03 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "프로필 조회 및 검색 성공", profileReadService.search(pageRequest));
    }

    @ApiOperation(value = "UC-PF-04 프로필 상세조회")
    @GetMapping(path = "/profile/{profileId}")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ProfileReadDto.Response> readProfileDetail(@PathVariable(name = "profileId") Long profileId) {
        System.out.println("UC-PF-04 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "프로필 상세조회 성공", profileReadService.readById(profileId));
    }

    @ApiOperation(value = "UC-PF-05 내 프로필 조회")
    @GetMapping(path = "/profile/my")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ProfileReadDto.Response> readMyProfile() {
        System.out.println("UC-PF-05 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "내 프로필 조회 성공", profileReadService.readMyProfile());
    }

    // 프로필 수정
    @ApiOperation(value = "UC-PF-06 프로필 상세 내용 수정")
    @PutMapping(path = "/profile")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Long> update(@RequestBody @Validated ProfileUpdateDto.Request request) {
        System.out.println("UC-PF-06 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "프로필 수정 성공", profileUpdateService.update(request));
    }

    // 프로필 공개/비공개
    @ApiOperation(value = "UC-PF-07 프로필 공개/비공개")
    @PutMapping(path = "/profile/visibility")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse updateIsOpen(@RequestBody @Validated ProfileUpdateDto.IsOpenRequest request) {
        System.out.println("UC-PF-07 요청");
        profileUpdateService.updateIsOpen(request);
        return new SuccessResponse(HttpStatus.OK.value(), "프로필 수정 성공");
    }

    @ApiOperation(value = "UC-PF-08 [사용자] 프로필 검색")
    @PostMapping(path = "/profile/filter")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readFiltered(@RequestBody @Validated SearchDto.Tag pageRequest) {
        System.out.println("UC-PF-08 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "모집글 필터링 성공", profileReadService.searchOnClientPage(pageRequest));
    }
}
