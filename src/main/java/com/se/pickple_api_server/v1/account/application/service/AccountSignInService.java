package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.common.infra.security.provider.JwtTokenResolver;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.application.dto.AccountSignInDto;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountSignInService {

    private final JwtTokenResolver jwtTokenResolver;
    private final AccountJpaRepository accountJpaRepository;

    public AccountSignInDto.Response signIn(Long id) {

        Account account = accountJpaRepository.findById(id)
                .orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));

        System.out.println("AccountSignInService.signIn");
        String token = jwtTokenResolver.createToken(String.valueOf(account.getAccountId()));
        return new AccountSignInDto.Response(token);
    }

}
