package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.account.application.dto.AccountUpdateDto;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.domain.entity.AccountType;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountUpdateService {

    private final AccountContextService accountContextService;
    private final AccountJpaRepository accountJpaRepository;

    public boolean update(AccountUpdateDto.Request request) {
        Account account = accountJpaRepository.findById(request.getAccountId()).orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));

        Boolean isAdmin = accountContextService.hasAuthority("ADMIN");

        if (!(accountContextService.isOwner(account) || isAdmin))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);

        if (request.getNewStudentId() != null)
            updateStudentId(account, request.getNewStudentId());
        if (request.getNewEmail() != null)
            updateEmail(account, request.getNewEmail());

        //관리자만
        if (request.getAccountType() != null && isAdmin)
            updateAccountType(account, request.getAccountType());

        accountJpaRepository.save(account);
        return true;
    }

    public void updateStudentId(Account account, String newStudentId) {
        account.updateStudentId(newStudentId);
    }

    public void updateEmail(Account account, String newEmail) { account.updateEmail(newEmail); }

    public void updateAccountType(Account account, String newAccountType) {
        account.updateAccountType(AccountType.valueOf(newAccountType));
    }

}
