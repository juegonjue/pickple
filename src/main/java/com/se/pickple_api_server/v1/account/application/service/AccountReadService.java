package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.domain.usecase.UseCase;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountReadService {

    private final AccountJpaRepository accountJpaRepository;

    public Account read(Long accountId){
        Optional<Account> member = accountJpaRepository.findById(accountId);
        return member.get();
    }

    public boolean isExist(Long accountId){
        return accountJpaRepository.findById(accountId).isPresent();
    }

}