package com.se.pickple_api_server.v1.apply.infra.repository;

import com.querydsl.jpa.JPQLQuery;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.apply.domain.entity.QApply;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplyQueryRepositoryImpl extends QuerydslRepositorySupport implements ApplyQueryRepository {
    public ApplyQueryRepositoryImpl() { super(Apply.class); }

    public Page<Apply> search(SearchDto.Apply searchRequest) {

        QApply apply = QApply.apply;
        JPQLQuery query = from(apply);

        if (searchRequest.getKeyword() != null) {
            query.where(
                    apply.review.containsIgnoreCase(searchRequest.getKeyword())
                .or(apply.recruitmentBoard.account.idString.containsIgnoreCase(searchRequest.getKeyword()))
            );
        }
        if (searchRequest.getIsContracted() != null) {
            query.where(apply.isContracted.eq(searchRequest.getIsContracted()));
        }
        if (searchRequest.getReviewState() != null) {
            query.where(apply.reviewState.eq(searchRequest.getReviewState()));
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<Apply> applyList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(applyList, pageable, totalElement);
    }
}
