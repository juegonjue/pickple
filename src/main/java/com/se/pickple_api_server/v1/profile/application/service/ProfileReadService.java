package com.se.pickple_api_server.v1.profile.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.profile.infra.repository.ProfileQueryRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileReadDto;
import com.se.pickple_api_server.v1.profile.application.error.ProfileErrorCode;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import com.se.pickple_api_server.v1.profile.infra.repository.ProfileJpaRepository;
import com.se.pickple_api_server.v1.recruitment.application.dto.RecruitmentBoardReadDto;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
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
    private final ProfileQueryRepository profileQueryRepository;

    // 내 프로필 조회
    public ProfileReadDto.Response readMyProfile() {
        Account account = accountContextService.getContextAccount();
        Profile myProfile = profileJpaRepository.findByAccount(account)
                .orElseThrow(() -> new BusinessException(ProfileErrorCode.NO_SUCH_PROFILE));
        return ProfileReadDto.Response.fromEntity(myProfile, true);
    }

    // 프로필 상세조회
    public ProfileReadDto.Response readById(Long profileId) {
        Profile profile = profileJpaRepository.findById(profileId)
                .orElseThrow(()->new BusinessException(ProfileErrorCode.NO_SUCH_PROFILE));
        return ProfileReadDto.Response.fromEntity(profile, accountContextService.hasAuthority("ADMIN"));
    }

    // 일반 사용자 프로필 목록조회 페이징
    public PageImpl readAll(Pageable pageable) {
        Page<Profile> profilePage = profileJpaRepository.findAllByIsOpenEquals(pageable, 1);
        List<ProfileReadDto.ListResponse> listResponseList = profilePage
                .get()
                .map(profile -> ProfileReadDto.ListResponse.fromEntity(profile))
                .collect(Collectors.toList());
        return new PageImpl(listResponseList, profilePage.getPageable(), profilePage.getTotalElements());
    }

    // 관리자 페이징
    // [관리자] 프로필 검색목록 페이징처리
    public PageImpl search(SearchDto.Request pageRequest) {
        Page<Profile> profilePage = profileQueryRepository.search(pageRequest);
        List<ProfileReadDto.SListResponse> responseList = profilePage
                .get()
                .map(profile -> ProfileReadDto.SListResponse.fromEntity(profile))
                .collect(Collectors.toList());
        return new PageImpl(responseList, profilePage.getPageable(), profilePage.getTotalElements());
    }

    //[사용자] 모집글 검색목록 페이징 처리 (키워드+태그들)
    public PageImpl searchOnClientPage(SearchDto.Tag pageRequest) {
        Page<Profile> profilePage = profileQueryRepository.filter(pageRequest);
        List<ProfileReadDto.ListResponse> responseList = profilePage
                .get()
                .map(profile -> ProfileReadDto.ListResponse.fromEntity(profile))
                .collect(Collectors.toList());

        return new PageImpl(responseList, profilePage.getPageable(), profilePage.getTotalElements());
    }
}
