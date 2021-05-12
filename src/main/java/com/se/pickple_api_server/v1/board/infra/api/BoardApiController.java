package com.se.pickple_api_server.v1.board.infra.api;

import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardCreateDto;
import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardReadDto;
import com.se.pickple_api_server.v1.board.application.service.RecruitmentBoardCreateService;
import com.se.pickple_api_server.v1.board.application.service.RecruitmentBoardReadService;
import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "게시물_모집글 관리")
public class BoardApiController {

    private final RecruitmentBoardCreateService recruitmentBoardCreateService;
    private final RecruitmentBoardReadService recruitmentBoardReadService;

    // UC-RB-01 모집글 등록
    @ApiOperation(value = "모집글 등록")
    @PostMapping(path = "/recboard")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated RecruitmentBoardCreateDto.Request request) {
        return new SuccessResponse(HttpStatus.CREATED.value(), "모집글 등록에 성공했습니다.", recruitmentBoardCreateService.create(request));
    }

    // TODO 페이지 타입 같이 넣어줘야
    // UC-RB-02 모집글 목록 조회 (페이징)
    @ApiOperation(value = "모집글 목록 조회 (페이징)")
    @GetMapping(path = "/recboard")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<PageImpl<RecruitmentBoardReadDto.ListResponse>> readAll(@Validated PageRequest pageRequest) {
        return new SuccessResponse(HttpStatus.OK.value(), "모집글 전체 목록 페이징 성공", recruitmentBoardReadService.readAll(pageRequest.of()));
    }

    // UC-RB-03 모집글 상세 조회
    @ApiOperation(value = "모집글 상세조회")
    @GetMapping(path = "/recboard/{boardId}")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<RecruitmentBoardReadDto.Response> readRecruitmentBoardById(@PathVariable(name = "boardId") Long boardId) {
        return new SuccessResponse(HttpStatus.OK.value(), "모집글 상세 조회 성공.", recruitmentBoardReadService.readById(boardId));
    }

}
