package com.se.pickple_api_server.v1.account.application.service;


import com.se.pickple_api_server.v1.account.application.dto.AccountDeleteDto;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.common.infra.springboot.advice.GlobalControllerAdvice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountDeleteService {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountContextService accountContextService;

    // 회원 삭제 (관리자)
    @Transactional
    public void delete(AccountDeleteDto.Request request) {
        Account account = findAccount(request.getIdString());
        if (!(accountContextService.isOwner(account) || accountContextService.hasAuthority("ADMIN")))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);
        updateIsDeleted(account);
        accountJpaRepository.save(account);
    }

    // 회원 delete시 UUID로 교체 + isDeleted=1
    public void updateIsDeleted(Account account) {
        String uuid = UUID.randomUUID().toString().replace("-","").substring(0,20);
        account.updateIsDeleted(uuid,1);
    }

    public Account findAccount(String idString) {
        return accountJpaRepository.findByIdString(idString)
                .orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
    }

}
