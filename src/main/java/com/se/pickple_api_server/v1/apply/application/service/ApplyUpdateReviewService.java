package com.se.pickple_api_server.v1.apply.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyUpdateDto;
import com.se.pickple_api_server.v1.apply.application.error.ApplyErrorCode;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.apply.domain.entity.ReviewState;
import com.se.pickple_api_server.v1.apply.infra.repository.ApplyJpaRepository;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.se.pickple_api_server.v1.apply.domain.entity.ReviewState.ACCEPT;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplyUpdateReviewService {

    private final AccountContextService accountContextService;
    private final ApplyJpaRepository applyJpaRepository;

    // [모집자] update, 내 모집글의 지원자에게 후기 작성 -> 후기 상태(before -> waiting),
    @Transactional
    public Long updateReview(ApplyUpdateDto.ReviewRequest request) {
        Apply apply = applyJpaRepository.findById(request.getApplyId())
                .orElseThrow(() -> new BusinessException(ApplyErrorCode.NO_SUCH_APPLY));

        if (!accountContextService.isOwner(apply.getRecruitmentBoard().getAccount().getAccountId()))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);

        if (apply.getIsContracted() != 1 || apply.getReviewState().toString().equals("ACCEPT"))
            throw new BusinessException(ApplyErrorCode.CANNOT_WRITE_REVIEW);

        apply.updateReview(request.getReview());
        apply.updateReviewState(ReviewState.WAITING);

        applyJpaRepository.save(apply);
        return apply.getApplyId();
    }

}
