package com.se.pickple_api_server.v1.tag.infra.repository;

import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import org.springframework.data.domain.Page;

public interface TagQueryRepository {
    Page<Tag> search(SearchDto.Request searchRequest);
}
