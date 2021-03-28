package com.se.pickple_api_server.v1.account.domain.usecase;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.domain.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.infra.dto.AccountCreateDto;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.common.domain.usecase.UseCase;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountCreateUseCase {

    private final AccountJpaRepository accountJpaRepository;

    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public Long signUp(AccountCreateDto.Request request) {

        if (accountJpaRepository.findByIdString(request.getId()).isPresent())
            throw new BusinessException(AccountErrorCode.DUPLICATED_ID);
        Account account = Account.builder()
                .idString(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                //.password(request.getPassword())
                .name(request.getName())
                .nickname(request.getNickname())
                .studentId(request.getStudentId())
                .type(request.getType())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .isCertified(request.getIsCertified())
                .membersOf(request.getMembersOf())
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
//                .name(request.getName())
//                .nickname(request.getNickname())
//                .studentId(request.getStudentId())
//                .type(request.getType())
//                .phoneNumber(request.getPhoneNumber())
//                .email(request.getEmail())
//                .isCertified(request.getIsCertified())
//                .membersOf(request.getMembersOf())
                .build();
        accountJpaRepository.save(account);
        return account.getAccountId();
    }
}
