package com.se.pickple_api_server.v1.recruitment.application.service;

import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.recruitment.application.dto.RecruitmentBoardUpdateDto;
import com.se.pickple_api_server.v1.recruitment.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitmentBoardUpdateService {

    private final AccountContextService accountContextService;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;
    private final AccountJpaRepository accountJpaRepository;

    @Transactional
    public boolean update(RecruitmentBoardUpdateDto.Request request) {
        Account account = accountJpaRepository.findById(request.getAccountId())
                .orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
        Boolean isAdmin = accountContextService.hasAuthority("ADMIN");
        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(request.getBoardId())
                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));

        if (!(accountContextService.isOwner(account) || isAdmin))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);

        recruitmentBoard.changeRecruitmentBoardInfo(request);

        recruitmentBoardJpaRepository.save(recruitmentBoard);
        return true;
    }

//    public void updateTitle(RecruitmentBoard recruitmentBoard, String title) {
//        recruitmentBoard.updateTitle(title);
//    }
}
