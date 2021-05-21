package com.se.pickple_api_server.v1.recruitment.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.recruitment.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitmentBoardDeleteService {
    private final AccountContextService accountContextService;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;

    @Transactional
    public void delete(Long boardId) {

        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));

        if (!(accountContextService.isOwner(recruitmentBoard.getAccount()) || accountContextService.hasAuthority("ADMIN")))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);

        recruitmentBoard.updateIsDeleted(1);
        recruitmentBoardJpaRepository.save(recruitmentBoard);
    }
}
