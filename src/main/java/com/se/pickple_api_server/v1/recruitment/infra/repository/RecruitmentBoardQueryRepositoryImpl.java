//package com.se.pickple_api_server.v1.board.infra.repository;
//
//import com.querydsl.jpa.JPQLQuery;
//import com.se.pickple_api_server.v1.recruitment.application.dto.RecruitmentBoardReadDto;
//import com.se.pickple_api_server.v1.recruitment.domain.entity.QRecruitmentBoard;
//import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
//import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardQueryRepository;
//import org.springframework.data.domain.Page;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.stereotype.Repository;
//
//
//@Repository
//public class RecruitmentBoardQueryRepositoryImpl extends QuerydslRepositorySupport implements RecruitmentBoardQueryRepository {
//    public RecruitmentBoardQueryRepositoryImpl() { super(RecruitmentBoard.class); }
//
//    @Override
//    public Page<RecruitmentBoard> search(RecruitmentBoardReadDto.SearchRequest searchRequest) {
//        QRecruitmentBoard recruitmentBoard = QRecruitmentBoard.recruitmentBoard;
//        JPQLQuery query = from(recruitmentBoard);
//
//
//        if (searchRequest.getKeyword() != null) {
//            query.where(
//                    recruitmentBoard.title.contains(searchRequest.getKeyword())
//                .or(recruitmentBoard.text.contains(searchRequest.getKeyword()))
//            );
//        }
//
//        query.where(recruitmentBoard.title.in(searchRequest.getTagStringList()));
//        if (searchRequest.getTagStringList() != null) {
//            query.where(recruitmentBoard.recruitmentBoardTagList.contains(searchRequest.getTagStringList());
//        }
//
//
//    }
//}
