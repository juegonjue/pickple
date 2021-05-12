package com.se.pickple_api_server.v1.apply.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.apply.infra.repository.ApplyJpaRepository;
import com.se.pickple_api_server.v1.board.infra.repository.RecruitmentBoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyReadService {

    private final AccountContextService accountContextService;
    private final ApplyJpaRepository applyJpaRepository;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;


    // TODO 모집글 상세조회에서 지원 여부
//    public ProfileReadDto.ExistResponse isExistInRecboard(Long boardId) {
//        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(boardId)
//                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));
//        Apply apply =
//    }

    // TODO 내 모집글에 들어온 지원서 목록

    // TODO 마이페이지 내가 한 지원
    //public

    // TODO [관리자] 사용자들의 지원 목록 페이징 (전체)

    // TODO [관리자] 사용자들의 지원목록에서 리뷰 신청 온것

}
