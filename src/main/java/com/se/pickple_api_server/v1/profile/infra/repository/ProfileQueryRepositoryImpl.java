package com.se.pickple_api_server.v1.profile.infra.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import com.se.pickple_api_server.v1.profile.domain.entity.QProfile;
import com.se.pickple_api_server.v1.profile.domain.entity.QProfileTag;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringTokenizer;

@Repository
public class ProfileQueryRepositoryImpl extends QuerydslRepositorySupport implements ProfileQueryRepository {
    public ProfileQueryRepositoryImpl()  { super(Profile.class); }

    @Override
    public Page<Profile> search(SearchDto.Request searchRequest) {
        QProfile profile = QProfile.profile;
        JPQLQuery query = from(profile);

        if (searchRequest.getKeyword() != null) {
            query.where(
                    profile.account.idString.containsIgnoreCase(searchRequest.getKeyword())
                .or(profile.kakaoId.containsIgnoreCase(searchRequest.getKeyword()))
                .or(profile.account.name.containsIgnoreCase(searchRequest.getKeyword()))
            );
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<Profile> profileList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(profileList, pageable, totalElement);
    }


    @Override
    public Page<Profile> filter(SearchDto.Tag searchRequest) {
        QProfile profile = QProfile.profile;
        QProfileTag profileTag = QProfileTag.profileTag;

        JPQLQuery query = from(profile)
                .leftJoin(profileTag).on(profile.profileId.eq(profileTag.profile.profileId));

        query.where(profile.isOpen.eq(1));

        if (searchRequest.getTags() != null) {
            BooleanBuilder builder = new BooleanBuilder();
            StringTokenizer st = new StringTokenizer(searchRequest.getTags(),",");
            while(st.hasMoreTokens()) {
                builder
                        .or(profileTag.tag.tagName.equalsIgnoreCase(st.nextToken()));
            }
            query.where(builder);
        }

        if (searchRequest.getKeyword() != null) {
            query.where(profile.account.name.containsIgnoreCase(searchRequest.getKeyword())
                    .or(profile.kakaoId.containsIgnoreCase(searchRequest.getKeyword()))
                    .or(profile.introduce.containsIgnoreCase(searchRequest.getKeyword())));
        }

        query.distinct();

        Pageable pageable = searchRequest.getPageRequest().of();
        List<Profile> profileList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(profileList, pageable, totalElement);
    }
}
