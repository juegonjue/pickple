package com.se.pickple_api_server.v1.apply.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyReadDto;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.apply.infra.repository.ApplyJpaRepository;
import com.se.pickple_api_server.v1.board.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.profile.infra.repository.ProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyReadService {

    private final AccountContextService accountContextService;
    private final ApplyJpaRepository applyJpaRepository;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;
    private final ProfileJpaRepository profileJpaRepository;


    // TODO 모집글 상세조회에서 지원 여부
//    public ProfileReadDto.ExistResponse isExistInRecboard(Long boardId) {
//        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(boardId)
//                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));
//        Apply apply =
//    }

    // TODO 내 모집글에 들어온 지원서 목록

    // TODO 마이페이지 내가 한 지원
    public List<ApplyReadDto.MyResponse> readAllMyApply() {
        Account account = accountContextService.getContextAccount();
        List<Apply> allMyApply = applyJpaRepository.findAllByProfile_Account(account);
        List<ApplyReadDto.MyResponse> allMyApplyReadDto
                = allMyApply
                .stream()
                .map(apply -> ApplyReadDto.MyResponse.fromEntity(apply))
                .collect(Collectors.toList());
        return allMyApplyReadDto;
    }

    // TODO [관리자] 사용자들의 지원 목록 페이징 (전체)

    // TODO [관리자] 사용자들의 지원목록에서 리뷰 신청 온것


}
