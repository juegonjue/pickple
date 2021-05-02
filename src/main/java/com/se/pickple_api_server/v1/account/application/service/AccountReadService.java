package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.account.application.dto.AccountReadDto;
import com.se.pickple_api_server.v1.account.application.dto.AccountSignInDto;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.common.infra.security.provider.JwtTokenResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountReadService {

    private final AccountJpaRepository accountJpaRepository;
    private final JwtTokenResolver jwtTokenResolver;

    public AccountReadDto.Response readById(Long accountId) {
        Account account = accountJpaRepository.findById(accountId).orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
        return AccountReadDto.Response.fromEntity(account);
    }


    public boolean isExist(Long accountId){
        return accountJpaRepository.findById(accountId).isPresent();
    }

    public Long findIdByIdString(String idString) {
        Account account = accountJpaRepository.findByIdString(idString).orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
        return account.getAccountId();
    }

    // 토큰 풀어헤쳐 account read 하기
    public AccountReadDto.Response getUserInfoByToken(String token) {
        Long userId = Long.parseLong(jwtTokenResolver.getUserId(token));
        Account account = accountJpaRepository.findById(userId).orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
        return AccountReadDto.Response.fromEntity(account);
    }

    public boolean isValidateToken(String token) {
        if (jwtTokenResolver.validateToken(token)) return true;
        return false;
    }
}