package com.se.pickple_api_server.v1.recruitment.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.recruitment.application.dto.RecruitmentBoardReadDto;
import com.se.pickple_api_server.v1.recruitment.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardQueryRepository;
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
public class RecruitmentBoardReadService {

    private final AccountContextService accountContextService;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;
    private final RecruitmentBoardQueryRepository recruitmentBoardQueryRepository;

    // 해당 페이지 상세조회
    public RecruitmentBoardReadDto.Response readById(Long boardId) {
        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(boardId)
                .orElseThrow(()->new BusinessException(BoardErrorCode.NO_SUCH_BOARD));
        return RecruitmentBoardReadDto.Response.fromEntity(recruitmentBoard);
    }

    // 페이징 목록조회 + isDeleted
    public PageImpl readAll(Pageable pageable) {

        Page<RecruitmentBoard> recruitmentBoardPage;

        if (accountContextService.hasAuthority("ADMIN")) {
            recruitmentBoardPage = recruitmentBoardJpaRepository.findAll(pageable);
        }
        else {
            recruitmentBoardPage = recruitmentBoardJpaRepository.findAllByIsDeletedEquals(pageable, 0);
        }

        List<RecruitmentBoardReadDto.ListResponse> listResponseList = recruitmentBoardPage
                .get()
                .map(recruitmentBoard -> RecruitmentBoardReadDto.ListResponse.fromEntity(recruitmentBoard))
                .collect(Collectors.toList());
        return new PageImpl(listResponseList, recruitmentBoardPage.getPageable(), recruitmentBoardPage.getTotalElements());
    }

    // 마이페이지 내가 쓴 모집글들 리스트 불러오기 --> boardId 가지고 있어야 함 + isDeleted
    public List<RecruitmentBoardReadDto.MyResponse> readAllMyRecboard() {
        Account account = accountContextService.getContextAccount();
        List<RecruitmentBoard> allMyRecruitmentBoard = recruitmentBoardJpaRepository.findAllByAccountAndIsDeletedEquals(account, 0);
        List<RecruitmentBoardReadDto.MyResponse> allMyRecboardReadDto
                = allMyRecruitmentBoard
                .stream()
                .map(recruitmentBoard -> RecruitmentBoardReadDto.MyResponse.fromEntity(recruitmentBoard))
                .collect(Collectors.toList());
        return allMyRecboardReadDto;
    }

    // [관리자] 모집글 검색목록 페이징처리
    public PageImpl search(SearchDto.Request pageRequest) {
        Page<RecruitmentBoard> recruitmentBoardPage = recruitmentBoardQueryRepository.search(pageRequest);
        List<RecruitmentBoardReadDto.ListResponse> responseList = recruitmentBoardPage
                .get()
                .map(recruitmentBoard -> RecruitmentBoardReadDto.ListResponse.fromEntity(recruitmentBoard))
                .collect(Collectors.toList());
        return new PageImpl(responseList, recruitmentBoardPage.getPageable(), recruitmentBoardPage.getTotalElements());
    }

}
