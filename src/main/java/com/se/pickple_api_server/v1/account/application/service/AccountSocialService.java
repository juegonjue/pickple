package com.se.pickple_api_server.v1.account.application.service;

import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.oauth.domain.error.OauthErrorCode;
import com.se.pickple_api_server.v1.oauth.infra.dto.userinfo.OauthUserInfo;
import com.se.pickple_api_server.v1.oauth.infra.oauth.OauthType;
import com.se.pickple_api_server.v1.oauth.infra.oauth.SocialOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountSocialService {
    private final AccountJpaRepository accountJpaRepository;
    private final List<SocialOauth> socialOauthList;

    // 소셜에서 가져온 회원정보에 대한 ID가 레포지토리에 존재하는지 확인
    public boolean isExist(String id) {
        System.out.println("AccountSocialService.isExist : 소셜에서 가져온 아이디 존재하는가");
        return accountJpaRepository.findByIdString(id).isPresent();
    }

    // 소셜에서 회원 정보 가져옴
    public OauthUserInfo getUserInfo(OauthType oauthType, String token) {
        SocialOauth socialOauth = findSocialOauthByType(oauthType);
        return socialOauth.getUserInfo(token);
    }

    // 지원하는 소셜 타입 존재하는지 확인
    private SocialOauth findSocialOauthByType(OauthType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new BusinessException(OauthErrorCode.NO_SUCH_OAUTH));
    }
}
