package com.se.pickple_api_server.v1.board.infra.repository;

import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardReadDto;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import org.springframework.data.domain.Page;

public interface RecruitmentBoardQueryRepository {
    Page<RecruitmentBoard> search(RecruitmentBoardReadDto.SearchRequest searchRequest);
}
