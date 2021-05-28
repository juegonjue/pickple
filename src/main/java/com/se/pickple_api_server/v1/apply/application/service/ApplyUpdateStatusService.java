package com.se.pickple_api_server.v1.apply.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyUpdateDto;
import com.se.pickple_api_server.v1.apply.application.error.ApplyErrorCode;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.apply.domain.type.ReviewState;
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
    public void contractStatus(ApplyUpdateDto.ContractRequest request) {

        Apply apply = applyJpaRepository.findById(request.getApplyId())
                .orElseThrow(() -> new BusinessException(ApplyErrorCode.NO_SUCH_APPLY));

        // 작성자가 아니면 상태 변경 불가
        if (!accountContextService.isOwner(apply.getRecruitmentBoard().getAccount().getAccountId()))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);

        apply.updateIsContracted(1);
        applyJpaRepository.save(apply);
    }


    // [관리자] update, 후기 승인 / 반려 (후기 보이게 하기) waiting -> accept / reject
    @Transactional
    public Long updateReviewStatus(ApplyUpdateDto.ReviewStatusRequest request) {

        Apply apply = applyJpaRepository.findById(request.getApplyId())
                .orElseThrow(() -> new BusinessException((ApplyErrorCode.NO_SUCH_APPLY)));

        // 후기 상태가 waiting 이거나 reject가 아니면 reviewState 변경 불가
        if (!(apply.getReviewState().toString().equals("WAITING") || apply.getReviewState().toString().equals("REJECT")))
            throw new BusinessException(ApplyErrorCode.STATE_NOT_WAITING);

        // 만약 request가 잘못된 형식이라면 유효하지 않다고 반환
        if (!(request.getReviewState().equals("REJECT") || request.getReviewState().equals("ACCEPT")))
            throw new BusinessException(ApplyErrorCode.INVALID_REVIEW_STATE);

        apply.updateReviewState(ReviewState.valueOf(request.getReviewState()));
        applyJpaRepository.save(apply);
        return apply.getApplyId();

    }


}
