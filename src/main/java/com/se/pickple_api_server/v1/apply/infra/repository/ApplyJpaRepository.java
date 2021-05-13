package com.se.pickple_api_server.v1.apply.infra.repository;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.apply.domain.entity.Apply;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyJpaRepository extends JpaRepository<Apply, Long> {
    List<Apply> findAllByProfile_Account(Account account);
    Optional<Apply> findByProfile_AccountAndRecruitmentBoard(Account account, RecruitmentBoard recruitmentBoard);
    Optional<Apply> findByProfileAndRecruitmentBoard(Profile profile, RecruitmentBoard recruitmentBoard);
}
