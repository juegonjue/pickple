package com.se.pickple_api_server.v1.recruitment.infra.repository;

import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import org.springframework.data.domain.Page;

public interface RecruitmentBoardQueryRepository {
    Page<RecruitmentBoard> search(SearchDto.Request searchRequest);
    Page<RecruitmentBoard> filter(SearchDto.Request searchRequest);
}
