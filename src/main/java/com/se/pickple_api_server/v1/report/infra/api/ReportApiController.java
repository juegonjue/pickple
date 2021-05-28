package com.se.pickple_api_server.v1.report.infra.api;

import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.report.application.dto.ReportCreateDto;
import com.se.pickple_api_server.v1.report.application.dto.ReportReadDto;
import com.se.pickple_api_server.v1.report.application.dto.ReportUpdateDto;
import com.se.pickple_api_server.v1.report.application.service.ReportCreateService;
import com.se.pickple_api_server.v1.report.application.service.ReportReadService;
import com.se.pickple_api_server.v1.report.application.service.ReportUpdateStatusService;
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
    private final ReportUpdateStatusService reportUpdateStatusService;

    @ApiOperation(value = "UC-RP-01 신고하기")
    @PostMapping(path = "/report")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated ReportCreateDto.Request request) {
        System.out.println("UC-RP-01 요청");
        return new SuccessResponse(HttpStatus.CREATED.value(),"신고 성공", reportCreateService.create(request));
    }

    @ApiOperation(value = "UC-RP-02 [관리자] 신고 조회 및 검색")
    @PostMapping(path = "/report/search")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readSearchReport(@RequestBody @Validated SearchDto.Report pageRequest) {
        System.out.println("UC-RP-02 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "신고 조회 및 검색 성공", reportReadService.search(pageRequest));
    }

    @ApiOperation(value = "UC-RP-03 신고 상세조회")
    @GetMapping(path = "/report/{reportId}")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ReportReadDto.Response> readReport(@PathVariable(name = "reportId") Long reportId) {
        System.out.println("UC-RP-03 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "신고 상세조회 성공", reportReadService.readById(reportId));
    }

    @ApiOperation(value = "UC-RP-04 내 신고 조회")
    @GetMapping(path = "/report/my")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ReportReadDto.MyResponse> readMyReport() {
        System.out.println("UC-RP-04 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "내 신고 조회 성공", reportReadService.readMyReport());
    }

    // 신고 처리
    @ApiOperation(value = "UC-RP-05 [관리자]신고 처리")
    @PutMapping(path = "/report/manage")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse manageReport(@RequestBody @Validated ReportUpdateDto.Request request) {
        System.out.println("UC-RP-05 요청");
        reportUpdateStatusService.updateReportStatus(request);
        return new SuccessResponse(HttpStatus.OK.value(), "신고 처리 성공");
    }

//    @ApiOperation(value = "UC-RP-02 [관리자]신고 목록 조회")
//    @GetMapping(path = "/report")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @ResponseStatus(value = HttpStatus.OK)
//    public SuccessResponse<Pageable> readAllReport (@Validated PageRequest pageRequest) {
//        return new SuccessResponse(HttpStatus.OK.value(), "신고 목록 조회 성공", reportReadService.readAll(pageRequest.of()));
//    }

}
