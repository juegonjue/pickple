package com.se.pickple_api_server.v1.report.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyReadDto;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.report.application.dto.ReportReadDto;
import com.se.pickple_api_server.v1.report.application.error.ReportErrorCode;
import com.se.pickple_api_server.v1.report.domain.entity.Report;
import com.se.pickple_api_server.v1.report.infra.repository.ReportJpaRepository;
import com.se.pickple_api_server.v1.report.infra.repository.ReportQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportReadService {

    private final AccountContextService accountContextService;
    private final ReportJpaRepository reportJpaRepository;
    private final ReportQueryRepository reportQueryRepository;

    // 신고 상세 조회
    public ReportReadDto.Response readById(Long reportId) {
        Report report = reportJpaRepository.findById(reportId)
                .orElseThrow(() -> new BusinessException(ReportErrorCode.NO_SUCH_REPORT));
        System.out.println(report.getReportId() + " : " + report.getBoard().getBoardId());
        return ReportReadDto.Response.fromEntity(report);
    }

    // 관리자 신고 목록 조회 (페이징)
    public PageImpl readAll(Pageable pageable) {
        Page<Report> reportPage = reportJpaRepository.findAll(pageable);
        List<ReportReadDto.ListResponse> listResponseList = reportPage
                .get()
                .map(report -> ReportReadDto.ListResponse.fromEntity(report))
                .collect(Collectors.toList());
        return new PageImpl(listResponseList, reportPage.getPageable(), reportPage.getTotalElements());
    }

    // 마이페이지 내가 한 신고 내역 및 현황 조회
    public List<ReportReadDto.MyResponse> readMyReport() {
        Account account = accountContextService.getContextAccount();
        List<Report> reportList = reportJpaRepository.findAllByReporter(account);
        List<ReportReadDto.MyResponse> reportListDto
                = reportList
                .stream()
                .map(report -> ReportReadDto.MyResponse.fromEntity(report))
                .collect(Collectors.toList());
        return reportListDto;
    }

    // 지원 검색목록 페이징처리 (관리자)
    public PageImpl search(SearchDto.Report pageRequest) {
        Page<Report> reportPage = reportQueryRepository.search(pageRequest);
        List<ReportReadDto.ListResponse> responseList = reportPage
                .get()
                .map(report -> ReportReadDto.ListResponse.fromEntity(report))
                .collect(Collectors.toList());
        return new PageImpl(responseList, reportPage.getPageable(), reportPage.getTotalElements());
    }

}
