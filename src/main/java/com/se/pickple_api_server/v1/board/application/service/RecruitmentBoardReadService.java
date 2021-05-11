package com.se.pickple_api_server.v1.board.application.service;

import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardReadDto;
import com.se.pickple_api_server.v1.board.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.board.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.bookmark.application.service.BookmarkReadService;
import com.se.pickple_api_server.v1.bookmark.infra.repository.BookmarkJpaRepository;
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
public class RecruitmentBoardReadService {

    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;


    // 해당 페이지 상세조회
    public RecruitmentBoardReadDto.Response readById(Long boardId) {
        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(boardId).orElseThrow(()->new BusinessException(BoardErrorCode.NO_SUCH_BOARD));
        return RecruitmentBoardReadDto.Response.fromEntity(recruitmentBoard);
    }

    // 페이징 목록조회
    public PageImpl readAll(Pageable pageable) {
        Page<RecruitmentBoard> recruitmentBoardPage = recruitmentBoardJpaRepository.findAll(pageable);
        List<RecruitmentBoardReadDto.ListResponse> listResponseList = recruitmentBoardPage
                .get()
                .map(recruitmentBoard -> RecruitmentBoardReadDto.ListResponse.fromEntity(recruitmentBoard))
                .collect(Collectors.toList());
        return new PageImpl(listResponseList, recruitmentBoardPage.getPageable(), recruitmentBoardPage.getTotalElements());
    }

    // 모집글 검색목록 페이징처리
//    public PageImpl search(RecruitmentBoardReadDto.SearchRequest pageRequest) {
//
//    }
}
