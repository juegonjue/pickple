package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.application.dto.AccountCreateDto;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.common.domain.usecase.UseCase;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountCreateService {

    private final AccountJpaRepository accountJpaRepository;

    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public Long signUp(AccountCreateDto.Request request) {

        if (accountJpaRepository.findByIdString(request.getId()).isPresent())
            throw new BusinessException(AccountErrorCode.DUPLICATED_ID);
        Account account = Account.builder()
                .idString(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .nickname(request.getNickname())
                .studentId(request.getStudentId())
                .accountType(request.getAccountType())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .isCertified(request.getIsCertified())
                .registerType(request.getRegisterType())
                .build();
        accountJpaRepository.save(account);
        return account.getAccountId();
    }

    @Transactional
    public Long signUpBySocial(OauthUserInfo oauthUserInfo) {

        if (accountJpaRepository.findByIdString(oauthUserInfo.getId()).isPresent())
            throw new BusinessException(AccountErrorCode.DUPLICATED_ID);
        Account account = Account.builder()
                .idString(oauthUserInfo.getId())
                .password(passwordEncoder.encode(oauthUserInfo.getId()))
                .build();
        accountJpaRepository.save(account);
        return account.getAccountId();
    }
}
