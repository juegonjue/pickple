package com.se.pickple_api_server.v1.report.infra.api;

import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.report.application.dto.ReportCreateDto;
import com.se.pickple_api_server.v1.report.application.dto.ReportReadDto;
import com.se.pickple_api_server.v1.report.application.service.ReportCreateService;
import com.se.pickple_api_server.v1.report.application.service.ReportReadService;
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
@Api(tags = "신고 관리")
public class ReportApiController {

    private final ReportCreateService reportCreateService;
    private final ReportReadService reportReadService;

    @ApiOperation(value = "신고하기")
    @PostMapping(path = "/report")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated ReportCreateDto.Request request) {
        return new SuccessResponse(HttpStatus.CREATED.value(),"신고 성공", reportCreateService.create(request));
    }

    @ApiOperation(value = "신고 상세조회")
    @GetMapping(path = "/report/{reportId}")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ReportReadDto.Response> readReport(@PathVariable(name = "reportId") Long reportId) {
        return new SuccessResponse(HttpStatus.OK.value(), "신고 상세조회 성공", reportReadService.readById(reportId));
    }

    @ApiOperation(value = "내 신고 조회")
    @GetMapping(path = "/report/my")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ReportReadDto.MyResponse> readMyReport() {
        return new SuccessResponse(HttpStatus.OK.value(), "내 신고 조회 성공", reportReadService.readMyReport());
    }

    @ApiOperation(value = "관리자 신고 목록 조회")
    @GetMapping(path = "/report")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readAllReport (@Validated PageRequest pageRequest) {
        return new SuccessResponse(HttpStatus.OK.value(), "관리자 신고 처리", reportReadService.readAll(pageRequest.of()));
    }

    // 신고 처리
    // BEFORE, NONE --> AFTER, ( , )

}
