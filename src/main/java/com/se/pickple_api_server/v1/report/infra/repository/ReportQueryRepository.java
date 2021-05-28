package com.se.pickple_api_server.v1.report.infra.repository;

import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.report.domain.entity.Report;
import org.springframework.data.domain.Page;

public interface ReportQueryRepository {
    Page<Report> search(SearchDto.Report searchRequest);
}
