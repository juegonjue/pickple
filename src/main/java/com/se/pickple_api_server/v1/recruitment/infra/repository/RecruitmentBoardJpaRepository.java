package com.se.pickple_api_server.v1.recruitment.infra.repository;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentBoardJpaRepository extends JpaRepository<RecruitmentBoard, Long> {
    List<RecruitmentBoard> findAllByAccountAndIsDeletedEquals(Account account, Integer isDeleted);
    Page<RecruitmentBoard> findAllByIsDeletedEquals(Pageable pageable, Integer isDeleted);

}
