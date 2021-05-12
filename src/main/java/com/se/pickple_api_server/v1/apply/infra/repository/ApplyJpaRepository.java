package com.se.pickple_api_server.v1.apply.infra.repository;

import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyJpaRepository extends JpaRepository<Apply, Long> {

}
