package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.common.infra.security.provider.JwtTokenResolver;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.application.dto.AccountSignInDto;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountSignInService {

    private final JwtTokenResolver jwtTokenResolver;
    private final AccountJpaRepository accountJpaRepository;

    // accountId로 로그인
    public AccountSignInDto.Response signIn(Long id) {
        Account account = accountJpaRepository.findById(id)
                .orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
        String token = jwtTokenResolver.createToken(String.valueOf(account.getAccountId()));
        return new AccountSignInDto.Response(token);
    }

    // 로그인 시 idString으로 로그인할 때 --> signIn(Long id)로 진행
    public AccountSignInDto.Response signIn(String idString) {
        Account account = accountJpaRepository.findByIdString(idString)
                .orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
        return signIn(account.getAccountId());
    }


}
