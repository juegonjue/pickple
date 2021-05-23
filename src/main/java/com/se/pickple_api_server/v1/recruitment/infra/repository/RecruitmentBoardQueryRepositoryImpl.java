package com.se.pickple_api_server.v1.recruitment.infra.repository;

import com.querydsl.jpa.JPQLQuery;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.recruitment.application.dto.RecruitmentBoardReadDto;
import com.se.pickple_api_server.v1.recruitment.domain.entity.QRecruitmentBoard;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RecruitmentBoardQueryRepositoryImpl extends QuerydslRepositorySupport implements RecruitmentBoardQueryRepository {
    public RecruitmentBoardQueryRepositoryImpl() { super(RecruitmentBoard.class); }

    @Override
    public Page<RecruitmentBoard> search(SearchDto.Request searchRequest) {
        QRecruitmentBoard recruitmentBoard = QRecruitmentBoard.recruitmentBoard;
        JPQLQuery query = from(recruitmentBoard);

        if (searchRequest.getKeyword() != null) {
            query.where(
                    recruitmentBoard.title.contains(searchRequest.getKeyword())
                .or(recruitmentBoard.text.contains(searchRequest.getKeyword())
                .or(recruitmentBoard.account.idString.contains(searchRequest.getKeyword())))
            );
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<RecruitmentBoard> recruitmentBoardList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(recruitmentBoardList, pageable, totalElement);
    }
}
