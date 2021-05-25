package com.se.pickple_api_server.v1.apply.infra.repository;

import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import org.springframework.data.domain.Page;

public interface ApplyQueryRepository {
    Page<Apply> search(SearchDto.Apply searchRequest);
}
