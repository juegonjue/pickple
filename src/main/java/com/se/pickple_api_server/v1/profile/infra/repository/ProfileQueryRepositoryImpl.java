package com.se.pickple_api_server.v1.profile.infra.repository;

import com.querydsl.jpa.JPQLQuery;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import com.se.pickple_api_server.v1.profile.domain.entity.QProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileQueryRepositoryImpl extends QuerydslRepositorySupport implements ProfileQueryRepository {
    public ProfileQueryRepositoryImpl()  { super(Profile.class); }

    public Page<Profile> search(SearchDto.Request searchRequest) {
        QProfile profile = QProfile.profile;
        JPQLQuery query = from(profile);

        if (searchRequest.getKeyword() != null) {
            query.where(
                    profile.account.idString.contains(searchRequest.getKeyword())
                .or(profile.kakaoId.contains(searchRequest.getKeyword()))
                .or(profile.account.name.contains(searchRequest.getKeyword()))
            );
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<Profile> profileList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(profileList, pageable, totalElement);
    }
}
