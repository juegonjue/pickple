package com.se.pickple_api_server.v1.board.application.service;

import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardCreateDto;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.board.infra.repository.RecruitmentBoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitmentBoardCreateService {

    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;

    @Transactional
    public Long create(RecruitmentBoardCreateDto.Request request) {
        LocalDateTime now = LocalDateTime.now();
        RecruitmentBoard recruitmentBoard = new RecruitmentBoard(
                request.getWriterId(),
                request.getTitle(),
                request.getText(),
                request.getBoardType(),
                0,
                0,
                request.getRecNumber(),
                0,
                request.getPaymentMax(),
                request.getWorkStartDate(),
                request.getWorkEndDate(),
                now,
                request.getRecEndDate()
        );

        recruitmentBoardJpaRepository.save(recruitmentBoard);
        return recruitmentBoard.getBoardId();
    }
}
