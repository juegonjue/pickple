package com.se.pickple_api_server.v1.apply.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyCreateDto;
import com.se.pickple_api_server.v1.apply.application.error.ApplyErrorCode;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.apply.domain.type.ReviewState;
import com.se.pickple_api_server.v1.apply.infra.repository.ApplyJpaRepository;
import com.se.pickple_api_server.v1.recruitment.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.profile.application.error.ProfileErrorCode;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import com.se.pickple_api_server.v1.profile.infra.repository.ProfileJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyCreateService {

    private final AccountContextService accountContextService;
    private final ProfileJpaRepository profileJpaRepository;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;

    // account로부터 profile 가져와 apply에 넣는것이 나을듯
    @Transactional
    public Long create(ApplyCreateDto.Request request) {

        // 해당 모집글에 내 지원서가 있으면 제출불가능
        Account account = accountContextService.getContextAccount();
        Profile profile = profileJpaRepository.findByAccount(account)
                .orElseThrow(() -> new BusinessException(ProfileErrorCode.NO_SUCH_PROFILE));
        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(request.getBoardId())
                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));

        if (applyJpaRepository.findByProfileAndRecruitmentBoard(profile, recruitmentBoard).isPresent())
            throw new BusinessException(ApplyErrorCode.DUPLICATED_APPLY);

        Apply apply = new Apply(
                profile,
                recruitmentBoard,
                0,
                ReviewState.BEFORE,
                0
        );

        applyJpaRepository.save(apply);
        return apply.getApplyId();
    }

}
