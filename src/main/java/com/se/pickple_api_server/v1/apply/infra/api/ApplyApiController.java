package com.se.pickple_api_server.v1.apply.infra.api;

import com.se.pickple_api_server.v1.apply.application.dto.ApplyCreateDto;
import com.se.pickple_api_server.v1.apply.application.service.ApplyCreateService;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "지원 관리")
public class ApplyApiController {

    private final ApplyCreateService applyCreateService;

    // [지원자] 지원 등록
    @ApiOperation(value = "지원 등록")
    @PostMapping(path = "/apply")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated ApplyCreateDto.Request request){
        return new SuccessResponse(HttpStatus.CREATED.value(), "지원서 등록 성공", applyCreateService.create(request));
    }


    // [지원자] 내가 한 지원+계약 조회



    // [모집자] 내 모집글의 지원+계약 조회

    // [모집자] 지원 상태 변경 (계약맺기)

    // [모집자] 내 모집글의 지원자에게 후기 작성

    // [관리자] 후기 승인 (후기 보이게 하기)


}
