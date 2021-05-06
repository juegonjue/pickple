package com.se.pickple_api_server.v1.board.infra.repository;

import com.se.pickple_api_server.v1.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository<T extends Board> extends JpaRepository<T, Long> {

}
