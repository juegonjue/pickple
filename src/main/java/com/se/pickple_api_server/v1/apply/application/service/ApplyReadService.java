package com.se.pickple_api_server.v1.apply.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.apply.application.dto.ApplyReadDto;
import com.se.pickple_api_server.v1.apply.application.error.ApplyErrorCode;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.apply.infra.repository.ApplyJpaRepository;
import com.se.pickple_api_server.v1.recruitment.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
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
public class ApplyReadService {

    private final AccountContextService accountContextService;
    private final ApplyJpaRepository applyJpaRepository;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;


    // 마이페이지 내가 한 지원 목록
    public List<ApplyReadDto.MyResponse> readAllMyApply() {
        Account account = accountContextService.getContextAccount();
        List<Apply> allMyApply = applyJpaRepository.findAllByProfile_AccountAndIsDeletedEquals(account,0);
        List<ApplyReadDto.MyResponse> allMyApplyReadDto
                = allMyApply
                .stream()
                .map(apply -> ApplyReadDto.MyResponse.fromEntity(apply))
                .collect(Collectors.toList());
        return allMyApplyReadDto;
    }

    // 현재 모집글에 내가 지원서를 냈는지 -> 현재 보드아이디 + account통해 지원서 존재하면 지원서아이디 리턴
    public ApplyReadDto.ExistResponse myApplyInRecboard(Long boardId) {
        Account account = accountContextService.getContextAccount();
        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));
        Apply apply = applyJpaRepository.findByProfile_AccountAndRecruitmentBoard(account, recruitmentBoard)
                .orElseThrow(() -> new BusinessException(ApplyErrorCode.NO_SUCH_APPLY));
        return ApplyReadDto.ExistResponse.fromEntity(apply);
    }

    // 특정 모집글에 들어온 지원서 목록
    public List<ApplyReadDto.MeResponse> readApplyInRecboard(Long boardId) {
        List<Apply> allApply = applyJpaRepository.findAllByRecruitmentBoard_BoardIdAndIsDeletedEquals(boardId, 0);
        List<ApplyReadDto.MeResponse> allApplyReadDto
                = allApply
                .stream()
                .map(apply -> ApplyReadDto.MeResponse.fromEntity(apply))
                .collect(Collectors.toList());
        return allApplyReadDto;
    }

    // [관리자] 사용자들의 지원 목록 페이징 (전체)
    public PageImpl readAll(Pageable pageable) {
        Page<Apply> applyPage = applyJpaRepository.findAll(pageable);
        List<ApplyReadDto.ListResponse> listResponseList = applyPage
                .get()
                .map(apply -> ApplyReadDto.ListResponse.formEntity(apply))
                .collect(Collectors.toList());
        return new PageImpl(listResponseList, applyPage.getPageable(), applyPage.getTotalElements());
    }


    // 한개의 지원 상세조회
    public ApplyReadDto.Response readApply(Long applyId) {
        Apply apply = applyJpaRepository.findById(applyId)
                .orElseThrow(() -> new BusinessException(ApplyErrorCode.NO_SUCH_APPLY));
        return ApplyReadDto.Response.fromEntity(apply);
    }

    // TODO [관리자] 사용자들의 지원목록에서 리뷰 신청 온것

    // 지원 목록

}
