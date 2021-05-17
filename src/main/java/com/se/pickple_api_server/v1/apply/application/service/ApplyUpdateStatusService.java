package com.se.pickple_api_server.v1.apply.application.service;

import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.apply.application.error.ApplyErrorCode;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.apply.infra.repository.ApplyJpaRepository;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplyUpdateStatusService {

    // ReviewState, Contract상태 등에 대한 변경 관리
    private final AccountContextService accountContextService;
    private final ApplyJpaRepository applyJpaRepository;


    // [모집자] update, 지원 상태 변경 (계약맺기) isContracted : 0 -> 1
    @Transactional
    public boolean updateContractedStatus(Long applyId) {

        Apply apply = applyJpaRepository.findById(applyId)
                .orElseThrow(() -> new BusinessException(ApplyErrorCode.NO_SUCH_APPLY));

        // 작성자가 아니면 상태 변경 불가
        if (!accountContextService.isOwner(apply.getRecruitmentBoard().getAccount().getAccountId()))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);
        apply.updateIsContracted(1);
        applyJpaRepository.save(apply);
        return true;
    }


    // [모집자] update, 내 모집글의 지원자에게 후기 작성 -> 후기 상태(before -> waiting), 후기가 승인된것만 후기 보이게 함

    // [관리자] update, 후기 승인 / 반려 (후기 보이게 하기) waiting -> accepted / rejected

}
