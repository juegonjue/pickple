package com.se.pickple_api_server.v1.recruitment.infra.repository;

import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoardTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentBoardTagJpaRepository extends JpaRepository<RecruitmentBoardTag, Long> {
    void deleteAllByTag_TagId(Long tagId);
}
