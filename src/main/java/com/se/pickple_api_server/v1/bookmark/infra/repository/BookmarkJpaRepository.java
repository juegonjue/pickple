package com.se.pickple_api_server.v1.bookmark.infra.repository;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.domain.entity.Board;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.bookmark.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookmarkJpaRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findAllByAccount_AccountId(Long accountId);
    Optional<Bookmark> findBookmarkByAccountAndBoard(Account account, RecruitmentBoard recruitmentBoard);
}
