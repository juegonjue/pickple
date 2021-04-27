package com.se.pickple_api_server.v1.account.application.service;


import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.domain.entity.AccountType;
import com.se.pickple_api_server.v1.account.domain.entity.RegisterType;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import com.se.pickple_api_server.v1.oauth.infra.oauth.OauthType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountCreateService {

    private final AccountJpaRepository accountJpaRepository;

    @Transactional
    public Long signUpBySocial(OauthUserInfo oauthUserInfo, OauthType oauthType) {
        Account account = Account.builder()
                .idString(oauthUserInfo.getId())
                .name(oauthUserInfo.getName())
                .accountType(AccountType.MEMBER)
                .email(oauthUserInfo.getEmail())
                .registerType(RegisterType.valueOf(oauthType.toString()))
                .build();
        accountJpaRepository.save(account);
        return account.getAccountId();
    }

//    @Transactional
//    public Long signUp(AccountCreateDto.Request request) {
//
//        if (accountJpaRepository.findByIdString(request.getId()).isPresent())
//            throw new BusinessException(AccountErrorCode.DUPLICATED_ID);
//        Account account = Account.builder()
//                .idString(request.getId())
//                .name(request.getName())
//                .studentId(request.getStudentId())
//                .accountType(request.getAccountType())
//                //.phoneNumber(request.getPhoneNumber())
//                .email(request.getEmail())
//                .isCertified(request.getIsCertified())
//                .registerType(request.getRegisterType())
//                .isDeleted((request.getIsDeleted()))
//                .build();
//        accountJpaRepository.save(account);
//        return account.getAccountId();
//    }

//    @Transactional
//    public Long signUpBySocial(OauthUserInfo oauthUserInfo) {
//
//        if (accountJpaRepository.findByIdString(oauthUserInfo.getId()).isPresent())
//            throw new BusinessException(AccountErrorCode.DUPLICATED_ID);
//        Account account = Account.builder()
//                .idString(oauthUserInfo.getId())
//                .password(passwordEncoder.encode(oauthUserInfo.getId()))
//                .build();
//        accountJpaRepository.save(account);
//        return account.getAccountId();
//    }
}
