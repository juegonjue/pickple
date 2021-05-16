package com.se.pickple_api_server.v1.recruitment.infra.repository;

import com.se.pickple_api_server.v1.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BoardJpaRepositoryImpl extends JpaRepository<Board, Long> {


}
