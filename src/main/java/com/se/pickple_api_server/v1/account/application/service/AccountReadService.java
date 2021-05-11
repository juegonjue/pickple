package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.account.application.dto.AccountReadDto;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.account.infra.repository.AccountQueryRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountReadService {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountQueryRepository accountQueryRepository;

    // id로 조회
    public AccountReadDto.Response readById(Long accountId) {
        return AccountReadDto.Response.fromEntity(findAccount(accountId));
    }

    // idString 으로 account 불러오기.
    public AccountReadDto.Response readByIdString(String idString) {
        return AccountReadDto.Response.fromEntity(findAccount(idString));
    }

    // idString 값으로 accountId 반환
    public Long findIdByIdString(String idString) {
        return findAccount(idString).getAccountId();
    }

    // context에서 내 정보 불러오기
    public AccountReadDto.Response getUserloading() {
        Long accountId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        return AccountReadDto.Response.fromEntity(findAccount(accountId));
    }

    // 회원목록 페이징처리
    public PageImpl readAll(Pageable pageable) {
        Page<Account> accountPage = accountJpaRepository.findAll(pageable);
        List<AccountReadDto.Response> responseList = accountPage
                .get()
                .map(account -> AccountReadDto.Response.fromEntity(account))
                .collect(Collectors.toList());
        return new PageImpl(responseList, accountPage.getPageable(), accountPage.getTotalElements());
    }


    public Account findAccount(String idString) {
        Account account = accountJpaRepository.findByIdString(idString).orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
        return account;
    }

    public Account findAccount(Long accountId) {
        Account account = accountJpaRepository.findById(accountId).orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
        return account;
    }

    // 회원 검색목록 페이징처리
    public PageImpl search(AccountReadDto.SearchRequest pageRequest) {
        Page<Account> accountPage = accountQueryRepository.search(pageRequest);
        List<AccountReadDto.Response>  responseList = accountPage
                .get()
                .map(account -> AccountReadDto.Response.fromEntity(account))
                .collect(Collectors.toList());
        return new PageImpl(responseList, accountPage.getPageable(), accountPage.getTotalElements());
    }

}