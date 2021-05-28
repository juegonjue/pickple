package com.se.pickple_api_server.v1.report.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.report.application.dto.ReportUpdateDto;
import com.se.pickple_api_server.v1.report.application.error.ReportErrorCode;
import com.se.pickple_api_server.v1.report.domain.entity.Report;
import com.se.pickple_api_server.v1.report.domain.type.ReportResult;
import com.se.pickple_api_server.v1.report.domain.type.ReportState;
import com.se.pickple_api_server.v1.report.infra.repository.ReportJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportUpdateStatusService {

    private final AccountContextService accountContextService;
    private final ReportJpaRepository reportJpaRepository;

    @Transactional
    public void updateReportStatus(ReportUpdateDto.Request request) {

        Account manager = accountContextService.getContextAccount();

        Report report = reportJpaRepository.findById(request.getReportId())
                .orElseThrow(() -> new BusinessException(ReportErrorCode.NO_SUCH_REPORT));

        List<String> reportResultList = Stream.of(ReportResult.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        for(String value : reportResultList) System.out.print(value + " ");

        if (!reportResultList.contains(request.getReportResult()))
            throw new BusinessException(ReportErrorCode.INVALID_INPUT);

        report.updateManager(manager);
        report.updateState(ReportState.AFTER);
        report.updateResult(ReportResult.valueOf(request.getReportResult()));

        reportJpaRepository.save(report);
    }
}
