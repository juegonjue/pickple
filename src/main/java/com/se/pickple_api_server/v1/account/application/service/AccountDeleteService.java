package com.se.pickple_api_server.v1.account.application.service;


import com.se.pickple_api_server.v1.account.application.dto.AccountDeleteDto;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountDeleteService {

    private final AccountJpaRepository accountJpaRepository;

    @Transactional
    public void delete(AccountDeleteDto.Request request) {
        Account account = accountJpaRepository.findByIdString(request.getIdString())
                .orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));

        // TODO 권한 확인 로직 추가 필요

        accountJpaRepository.delete(account);
    }
}
