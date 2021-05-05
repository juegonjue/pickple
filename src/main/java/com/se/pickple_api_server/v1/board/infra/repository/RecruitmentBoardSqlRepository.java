package com.se.pickple_api_server.v1.board.infra.repository;

import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import org.springframework.data.repository.Repository;

public interface RecruitmentBoardSqlRepository extends Repository<RecruitmentBoard, Long> {

}
