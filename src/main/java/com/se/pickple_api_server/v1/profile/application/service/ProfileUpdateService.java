package com.se.pickple_api_server.v1.profile.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileUpdateDto;
import com.se.pickple_api_server.v1.profile.application.error.ProfileErrorCode;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import com.se.pickple_api_server.v1.profile.domain.entity.ProfileTag;
import com.se.pickple_api_server.v1.profile.infra.repository.ProfileJpaRepository;
import com.se.pickple_api_server.v1.tag.application.dto.TagCreateDto;
import com.se.pickple_api_server.v1.tag.application.error.TagErrorCode;
import com.se.pickple_api_server.v1.tag.infra.repository.TagJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileUpdateService {

    private final AccountContextService accountContextService;
    private final ProfileJpaRepository profileJpaRepository;
    private final TagJpaRepository tagJpaRepository;

    @Transactional
    public Long update(ProfileUpdateDto.Request request) {

        Account account = accountContextService.getContextAccount();
        Profile profile = profileJpaRepository.findByAccount(account)
                .orElseThrow(() -> new BusinessException(ProfileErrorCode.NO_SUCH_PROFILE));

        // 중복검증
        if (profileJpaRepository.findByKakaoId(request.getKakaoId()).isPresent())
            throw new BusinessException((ProfileErrorCode.DUPLICATED_KAKAOID));
        if (profileJpaRepository.findByWorkEmail(request.getWorkEmail()).isPresent())
            throw new BusinessException((ProfileErrorCode.DUPLICATED_WORKEMAIL));
        if (request.getBlog() != null) {
            if (profileJpaRepository.findByBlog(request.getBlog()).isPresent())
                throw new BusinessException((ProfileErrorCode.DUPLICATED_BLOG));
        }

        List<ProfileTag> tags = getTags(request.getTagList());

        profile.update(
                request.getKakaoId(),
                request.getWorkEmail(),
                request.getBlog(),
                request.getIntroduce(),
                request.getIsOpen(),
                tags
        );

        profileJpaRepository.save(profile);
        return profile.getProfileId();
    }

    @Transactional
    public void updateIsOpen(ProfileUpdateDto.IsOpenRequest request) {
        Account account = accountContextService.getContextAccount();
        Profile profile = profileJpaRepository.findByAccount(account)
                .orElseThrow(() -> new BusinessException(ProfileErrorCode.NO_SUCH_PROFILE));

        profile.updateIsOpen(request.getIsOpen());
        profileJpaRepository.save(profile);
    }

    private List<ProfileTag> getTags(List<TagCreateDto.TagDto> tagDtoList) {
        return tagDtoList.stream()
                .map(tag -> ProfileTag.builder()
                    .tag(tagJpaRepository.findById(tag.getTagId())
                        .orElseThrow(() -> new BusinessException(TagErrorCode.NO_SUCH_TAG)))
                    .build()
                )
                .collect(Collectors.toList());
    }
}
