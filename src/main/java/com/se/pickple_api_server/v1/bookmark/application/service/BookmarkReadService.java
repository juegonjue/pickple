package com.se.pickple_api_server.v1.bookmark.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.board.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.board.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.bookmark.application.dto.BookmarkReadDto;
import com.se.pickple_api_server.v1.bookmark.application.error.BookmarkErrorCode;
import com.se.pickple_api_server.v1.bookmark.domain.entity.Bookmark;
import com.se.pickple_api_server.v1.bookmark.infra.repository.BookmarkJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkReadService {

    private final BookmarkJpaRepository bookmarkJpaRepository;
    private final AccountContextService accountContextService;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;


    // (accountId 로)내 모든 북마크 불러오기
    public List<BookmarkReadDto.Response> readAllMyBookmark() {
        Account account = accountContextService.getContextAccount();
        List<Bookmark> allMyBookmarks = bookmarkJpaRepository.findAllByAccount(account);
        List<BookmarkReadDto.Response> allMyBookmarksReadDto
                = allMyBookmarks
                .stream()
                .map(bookmark -> BookmarkReadDto.Response.fromEntity(bookmark))
                .collect(Collectors.toList());

        return allMyBookmarksReadDto;
    }

    // TODO 현재 모집글의 내 북마크여부 가져오기
    public BookmarkReadDto.PresentResponse readExistInRecboard(Long boardId) {
        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));
        Bookmark bookmark = bookmarkJpaRepository.findBookmarkByAccountAndBoard(accountContextService.getContextAccount(), recruitmentBoard)
                .orElseThrow(() -> new BusinessException(BookmarkErrorCode.NO_SUCH_BOOKMARK));

        return BookmarkReadDto.PresentResponse.fromEntity(bookmark);

    }
}
