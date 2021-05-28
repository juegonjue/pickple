package com.se.pickple_api_server.v1.tag.infra.repository;

import com.querydsl.jpa.JPQLQuery;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.report.domain.entity.Report;
import com.se.pickple_api_server.v1.tag.domain.entity.QTag;
import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagQueryRepositoryImpl extends QuerydslRepositorySupport implements TagQueryRepository {
    public TagQueryRepositoryImpl() { super(Tag.class); }

    @Override
    public Page<Tag> search(SearchDto.Request searchRequest) {

        QTag tag = QTag.tag;
        JPQLQuery query = from(tag);

        if (searchRequest.getKeyword() != null) {
            query.where(tag.tagName.containsIgnoreCase(searchRequest.getKeyword()));
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<Tag> tagList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(tagList, pageable, totalElement);
    }
}
