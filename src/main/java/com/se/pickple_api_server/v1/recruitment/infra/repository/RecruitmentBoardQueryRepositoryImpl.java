//package com.se.pickple_api_server.v1.board.infra.repository;
//
//import com.querydsl.jpa.JPQLQuery;
//import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardReadDto;
//import com.se.pickple_api_server.v1.board.domain.entity.QRecruitmentBoard;
//import com.se.pickple_api_server.v1.board.recruitment.domain.entity.RecruitmentBoard;
//import org.springframework.data.domain.Page;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.stereotype.Repository;
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
//        if (searchRequest.getKeyword() != null) {
//            query.where(
//                    recruitmentBoard.title.contains(searchRequest.getKeyword())
//                    .or(recruitmentBoard.text.contains(searchRequest.getKeyword()))
//            );
//        }
//
////        if (!searchRequest.getTagString().isEmpty()) {
////            searchRequest.getTagString()
////                    .stream()
////                    .map(tag -> query.where(recruitmentBoard.recruitmentBoardTagList.contains(tag)));
////
////            )
////        }
//
//        if (!searchRequest.getTagString().isEmpty()) {
//            query.where(
//
//            )
//    }
//}
