package com.se.pickple_api_server.v1.bookmark.application.service;

import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.board.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.board.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.bookmark.application.dto.BookmarkCreateDto;
import com.se.pickple_api_server.v1.bookmark.application.error.BookmarkErrorCode;
import com.se.pickple_api_server.v1.bookmark.domain.entity.Bookmark;
import com.se.pickple_api_server.v1.bookmark.infra.repository.BookmarkJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkCreateService {

    private final AccountContextService accountContextService;
    private final BookmarkJpaRepository bookmarkJpaRepository;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;

    @Transactional
    public Long create(BookmarkCreateDto.Request request) {

        Account account = accountContextService.getContextAccount();
        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(request.getBoardId())
                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));

        if (bookmarkJpaRepository.findBookmarkByAccountAndBoard(account,recruitmentBoard).isPresent())
            throw new BusinessException(BookmarkErrorCode.DUPLICATED_BOOKMARK);

        Bookmark bookmark = new Bookmark(
                account,
                recruitmentBoard
        );

        bookmarkJpaRepository.save(bookmark);
        return bookmark.getBookmarkId();

    }

}
