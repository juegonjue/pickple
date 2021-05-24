package com.se.pickple_api_server.v1.apply.infra.api;

import com.se.pickple_api_server.v1.apply.application.dto.ApplyCreateDto;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyDeleteDto;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyReadDto;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyUpdateDto;
import com.se.pickple_api_server.v1.apply.application.service.*;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileReadDto;
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
@Api(tags = "지원 관리")
public class ApplyApiController {

    private final ApplyCreateService applyCreateService;
    private final ApplyReadService applyReadService;
    private final ApplyUpdateStatusService applyUpdateStatusService;
    private final ApplyUpdateReviewService applyUpdateReviewService;
    private final ApplyDeleteService applyDeleteService;

    @ApiOperation(value = "AC-AP-01 지원 등록")
    @PostMapping(path = "/apply")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated ApplyCreateDto.Request request){
        System.out.println("AC-AP-01 요청");
        return new SuccessResponse(HttpStatus.CREATED.value(), "지원서 등록 성공", applyCreateService.create(request));
    }

    @ApiOperation(value = "AC-AP-02 [관리자] 지원 검색")
    @GetMapping(path = "/apply/search")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readSearchApply(@Validated SearchDto.Apply pageRequest) {
        System.out.println("AC-AP-02 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "지원 조회 및 검색 성공", applyReadService.search(pageRequest));
    }

    @ApiOperation(value = "AC-AP-03 지원 상세 조회")
    @GetMapping(path = "/apply/{applyId}")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ApplyReadDto.Response> readApply(@PathVariable(name = "applyId") Long applyId) {
        System.out.println("AC-AP-03 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "지원 상세 조회", applyReadService.readApply(applyId));
    }


    // 지원 취소
    @ApiOperation(value = "AC-AP-04 지원 취소")
    @DeleteMapping(path = "/apply")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse cancelApply(@RequestBody @Validated ApplyDeleteDto.Request request) {
        System.out.println("AC-AP-04 요청");
        applyDeleteService.delete(request);
        return new SuccessResponse(HttpStatus.OK.value(), "지원 취소 성공");
    }

    @ApiOperation(value = "AC-AP-05 계약 맺기")
    @PutMapping(path = "/apply/contract")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse makeContract(@RequestBody @Validated ApplyUpdateDto.ContractRequest request) {
        System.out.println("AC-AP-05 요청");
        applyUpdateStatusService.contractStatus(request);
        return new SuccessResponse(HttpStatus.OK.value(), "계약 맺기 성공");
    }

    @ApiOperation(value = "AC-AP-06 프로필에 후기 작성")
    @PutMapping(path = "/apply/review")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse writeReview(@RequestBody @Validated ApplyUpdateDto.ReviewRequest request) {
        System.out.println("AC-AP-06 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "계약 후기 작성 성공", applyUpdateReviewService.updateReview(request));
    }

    @ApiOperation(value = "AC-AP-07 후기 처리")
    @PutMapping(path = "/apply/manage")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse manageReview(@RequestBody @Validated ApplyUpdateDto.ReviewStatusRequest request) {
        System.out.println("AC-AP-07 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "후기 처리 성공", applyUpdateStatusService.updateReviewStatus(request));
    }

    @ApiOperation(value = "AC-AP-08 (특정 모집글에 들어온) 지원 목록 조회")
    @GetMapping(path = "/apply/board/{boardId}")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ApplyReadDto.MeResponse> readApplyInRecboard(@PathVariable(name = "boardId") Long boardId) {
        System.out.println("AC-AP-08 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "특정 모집글 지원 목록 조회 성공", applyReadService.readApplyInRecboard(boardId));
    }

    @ApiOperation(value = "AC-AP-09 내 지원 조회")
    @GetMapping(path = "/apply/my")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ApplyReadDto.MyResponse> readMyApply() {
        System.out.println("AC-AP-09 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "내 지원, 계약 목록 조회 성공", applyReadService.readAllMyApply());
    }

    @ApiOperation(value = "AC-AP-10 현재 모집글의 내 지원여부")
    @GetMapping(path = "/apply/my/{boardId}")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ProfileReadDto.ExistResponse> readExist(@PathVariable(name = "boardId") Long boardId) {
        System.out.println("AC-AP-10 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "현재 모집글에서 내 지원 여부 조회 성공 ", applyReadService.myApplyInRecboard(boardId));
    }

    @ApiOperation(value = "AC-AP-11 특정 사용자에 대한 후기 보기")
    @GetMapping(path = "/apply/review/{profileId}")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse readReviewOnProfile(@PathVariable(name = "profileId") Long profileId) {
        System.out.println("AC-AP-11 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "특정 사용자 후기 보기 성공", applyReadService.readReviewByProfileId(profileId));
    }

    // [관리자] 모든 지원 조회
//    @ApiOperation(value = "지원 목록 조회 페이징")
//    @GetMapping(path = "/apply")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @ResponseStatus(value = HttpStatus.OK)
//    public SuccessResponse<Pageable> readAllApply(@Validated PageRequest pageRequest) {
//        return new SuccessResponse(HttpStatus.OK.value(), "지원 목록 조회 페이징 성공", applyReadService.readAll(pageRequest.of()));
//    }


}
