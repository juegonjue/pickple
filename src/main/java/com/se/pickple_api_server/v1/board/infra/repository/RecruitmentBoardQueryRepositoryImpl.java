//package com.se.pickple_api_server.v1.board.infra.repository;
//
//import com.querydsl.jpa.JPQLQuery;
//import com.se.pickple_api_server.v1.account.application.dto.AccountReadDto;
//import com.se.pickple_api_server.v1.account.domain.entity.Account;
//import com.se.pickple_api_server.v1.account.infra.repository.AccountQueryRepository;
//import com.se.pickple_api_server.v1.account.infra.repository.AccountQueryRepositoryImpl;
//import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardReadDto;
//import com.se.pickple_api_server.v1.board.domain.entity.QRecruitmentBoard;
//import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
//import org.springframework.data.domain.Page;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.security.core.parameters.P;
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
//        if (searchRequest.getBoardTitle() != null) {
//            query.where(recruitmentBoard.title.contains(searchRequest.getBoardTitle()));
//        }
//        if (searchRequest.getBoardText() != null) {
//            query.where(recruitmentBoard.text.contains(searchRequest.getBoardText()));
//        }
//        if
//    }
//}
