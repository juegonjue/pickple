package com.se.pickple_api_server.v1.report.application.service;

import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.recruitment.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
//import com.se.pickple_api_server.v1.recruitment.infra.repository.BoardJpaRepository;
import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.report.application.dto.ReportCreateDto;
import com.se.pickple_api_server.v1.report.domain.entity.Report;
import com.se.pickple_api_server.v1.report.domain.type.ReportResult;
import com.se.pickple_api_server.v1.report.domain.type.ReportState;
import com.se.pickple_api_server.v1.report.infra.repository.ReportJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportCreateService {

    private final AccountContextService accountContextService;
    private final AccountJpaRepository accountJpaRepository;
    private final ReportJpaRepository reportJpaRepository;
//    private final BoardJpaRepository boardJpaRepository;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;
//
//    @Transactional
//    public Long create(ReportCreateDto.Request request) {
//
//        Account reporter = accountContextService.getContextAccount();
//        Board board = boardJpaRepository.findById(request.getBoardId())
//                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));
//
//        Account reported = accountJpaRepository.findById(request.getReported())
//                .orElseThrow(() -> new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
//
//        Report report = new Report(
//                board,
//                request.getText(),
//                ReportState.BEFORE,
//                ReportResult.NONE,
//                reporter,
//                reported
//        );
//
//        reportJpaRepository.save(report);
//        return report.getReportId();
//    }
}
