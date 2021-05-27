package com.se.pickple_api_server.v1.recruitment.infra.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.recruitment.domain.entity.QRecruitmentBoard;
import com.se.pickple_api_server.v1.recruitment.domain.entity.QRecruitmentBoardTag;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringTokenizer;


@Repository
public class RecruitmentBoardQueryRepositoryImpl extends QuerydslRepositorySupport implements RecruitmentBoardQueryRepository {
    public RecruitmentBoardQueryRepositoryImpl() { super(RecruitmentBoard.class); }

    @Override
    public Page<RecruitmentBoard> search(SearchDto.Request searchRequest) {
        QRecruitmentBoard recruitmentBoard = QRecruitmentBoard.recruitmentBoard;
        JPQLQuery query = from(recruitmentBoard);

        if (searchRequest.getKeyword() != null) {
            query.where(
                    recruitmentBoard.title.containsIgnoreCase(searchRequest.getKeyword())
                .or(recruitmentBoard.text.containsIgnoreCase(searchRequest.getKeyword())
                .or(recruitmentBoard.account.idString.containsIgnoreCase(searchRequest.getKeyword())))
            );
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<RecruitmentBoard> recruitmentBoardList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(recruitmentBoardList, pageable, totalElement);
    }


    // TODO 필터링
    @Override
    public Page<RecruitmentBoard> filter(SearchDto.Tag searchRequest) {

        QRecruitmentBoard recruitmentBoard = QRecruitmentBoard.recruitmentBoard;
        QRecruitmentBoardTag recruitmentBoardTag = QRecruitmentBoardTag.recruitmentBoardTag;

        JPQLQuery query = from(recruitmentBoard)
                .join(recruitmentBoardTag).on(recruitmentBoard.boardId.eq(recruitmentBoardTag.recruitmentBoard.boardId));

//        if (searchRequest.getTagList() != null) {
//            query.select().distinct();
//            query.from(recruitmentBoard)
//                    .join(recruitmentBoardTag)
//                    .on(recruitmentBoard.boardId.eq(recruitmentBoardTag.recruitmentBoard.boardId))
//                    .join(tag)
//                    .on(recruitmentBoardTag.recruitmentBoardTagId.eq(tag.tagId));
//            query.where(tag.tagName.in(searchRequest.getTagList()));
//        }

        BooleanBuilder builder = new BooleanBuilder();
        if (searchRequest.getTags() != null) {
            StringTokenizer st = new StringTokenizer(searchRequest.getTags(),",");
            while(st.hasMoreTokens()) {
                String temp = st.nextToken();
                System.out.println(temp);
                builder
                        .or(recruitmentBoardTag.tag.tagName.equalsIgnoreCase(temp));
            }
            query.where(builder);
        }

        if (searchRequest.getKeyword() != null) {
            query.where(recruitmentBoard.title.containsIgnoreCase(searchRequest.getKeyword())
                    .or(recruitmentBoard.text.containsIgnoreCase(searchRequest.getKeyword())));
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<RecruitmentBoard> recruitmentBoardList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(recruitmentBoardList, pageable, totalElement);

    }
}
