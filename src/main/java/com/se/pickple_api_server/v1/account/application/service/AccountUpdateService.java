package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.account.application.dto.AccountUpdateDto;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountUpdateService {

    private final AccountJpaRepository accountJpaRepository;

    public boolean update(AccountUpdateDto.Request request) {
        Account account = accountJpaRepository.findByIdString(request.getId()).orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));

        // TODO 권한 확인 로직 추가 필요
        if (request.getId() != null)
            updateStudentId(account, request.getNewStudentId());
        if (request.getEmail() != null)
            updateEmail(account, request.getEmail());

        accountJpaRepository.save(account);
        return true;
    }

    public void updateStudentId(Account account, String newStudentId) {
        account.updateStudentId(newStudentId);
    }

    public void updateEmail(Account account, String newEmail) { account.updateEmail(newEmail); }
}
