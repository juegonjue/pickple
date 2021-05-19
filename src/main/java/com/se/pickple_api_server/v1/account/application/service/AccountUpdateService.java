package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.account.application.dto.AccountUpdateDto;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountUpdateService {

    private final AccountContextService accountContextService;
    private final AccountJpaRepository accountJpaRepository;

    @Transactional
    public boolean update(AccountUpdateDto.Request request) {

        Account account = accountJpaRepository.findById(request.getAccountId())
                .orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
        Boolean isAdmin = accountContextService.hasAuthority("ADMIN");

        if (!(accountContextService.isOwner(account) || isAdmin))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);

        account.changeAccountInfo(request);

        accountJpaRepository.save(account);
        return true;

    }

}
