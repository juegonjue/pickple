package com.se.pickple_api_server.v1.bookmark.infra.repository;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.bookmark.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkJpaRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findAllByAccount(Account account);
    Optional<Bookmark> findBookmarkByAccountAndBoard(Account account, RecruitmentBoard recruitmentBoard);
}
