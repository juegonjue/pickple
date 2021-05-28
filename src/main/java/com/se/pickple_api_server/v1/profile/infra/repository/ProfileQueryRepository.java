package com.se.pickple_api_server.v1.profile.infra.repository;

import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import org.springframework.data.domain.Page;

public interface ProfileQueryRepository {
    Page<Profile> search(SearchDto.Request searchRequest);
    Page<Profile> filter(SearchDto.Tag searchRequest);
}
