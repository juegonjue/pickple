package com.se.pickple_api_server.v1.board.infra.api;

import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardCreateDto;
import com.se.pickple_api_server.v1.board.application.service.RecruitmentBoardCreateService;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "게시물 관리")
public class BoardApiController {

    private final RecruitmentBoardCreateService recruitmentBoardCreateService;

    // UC-RB-01 모집글 등록
    @ApiOperation(value = "모집글 등록")
    @PostMapping(path = "/recboard")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated RecruitmentBoardCreateDto.Request request) {
        return new SuccessResponse(HttpStatus.CREATED.value(), "모집글 등록에 성공했습니다.", recruitmentBoardCreateService.create(request));
    }


    // UC-RB-02 모집글 조회


}
