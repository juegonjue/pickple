package com.se.pickple_api_server.v1.profile.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileReadDto;
import com.se.pickple_api_server.v1.profile.application.error.ProfileErrorCode;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import com.se.pickple_api_server.v1.profile.infra.repository.ProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileReadService {

    private final AccountContextService accountContextService;
    private final ProfileJpaRepository profileJpaRepository;

    // 내 프로필 조회
    public ProfileReadDto.Response readMyProfile() {
        Account account = accountContextService.getContextAccount();
        Profile myProfile = profileJpaRepository.findByAccount(account)
                .orElseThrow(()->new BusinessException(ProfileErrorCode.NO_SUCH_PROFILE));
        return ProfileReadDto.Response.fromEntity(myProfile);
    }

    // 프로필 상세조회
    public ProfileReadDto.Response readById(Long profileId) {
        Profile profile = profileJpaRepository.findById(profileId)
                .orElseThrow(()->new BusinessException(ProfileErrorCode.NO_SUCH_PROFILE));
        return ProfileReadDto.Response.fromEntity(profile);
    }

    // 프로필 목록조회 페이징
    public PageImpl readAll(Pageable pageable) {
        Page<Profile> profilePage = profileJpaRepository.findAll(pageable);
        List<ProfileReadDto.ListResponse> listResponseList = profilePage
                .get()
                .map(profile -> ProfileReadDto.ListResponse.fromEntity(profile))
                .collect(Collectors.toList());
        return new PageImpl(listResponseList, profilePage.getPageable(), profilePage.getTotalElements());
    }

    //  프로필 존재 여부 확인
    public Boolean isExist(Account account){
        if (profileJpaRepository.findByAccount(account).isPresent()) return true;
        return false;
    }

}
