package com.se.pickple_api_server.v1.apply.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyDeleteDto;
import com.se.pickple_api_server.v1.apply.application.error.ApplyErrorCode;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.apply.infra.repository.ApplyJpaRepository;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyDeleteService {

    private final AccountContextService accountContextService;
    private final ApplyJpaRepository applyJpaRepository;

    @Transactional
    public void delete(ApplyDeleteDto.Request request) {
        Apply apply = applyJpaRepository.findById(request.getApplyId())
                .orElseThrow(() -> new BusinessException(ApplyErrorCode.NO_SUCH_APPLY));

        if (!accountContextService.isOwner(apply.getProfile().getAccount()))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);

        if (apply.getIsContracted()==1)
            throw new BusinessException(ApplyErrorCode.CANNOT_CANCEL_APPLY);

        apply.updateIsDeleted(1);
        applyJpaRepository.save(apply);
    }
 }
