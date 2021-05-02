package com.se.pickple_api_server.v1.board.application.service;

import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardReadDto;
import com.se.pickple_api_server.v1.board.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.board.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentBoardReadService {

    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;

    public RecruitmentBoardReadDto.Response readById(Long boardId) {
        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(boardId).orElseThrow(()->new BusinessException(BoardErrorCode.NO_SUCH_BOARD));
        return RecruitmentBoardReadDto.Response.fromEntity(recruitmentBoard);
    }

}
