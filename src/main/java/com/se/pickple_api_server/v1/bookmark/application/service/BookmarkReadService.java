package com.se.pickple_api_server.v1.bookmark.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.bookmark.application.dto.BookmarkReadDto;
import com.se.pickple_api_server.v1.bookmark.domain.entity.Bookmark;
import com.se.pickple_api_server.v1.bookmark.infra.repository.BookmarkJpaRepository;
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

    // TODO 현재 모집글의 내 북마크여부 가져오기

    // (accountId 로)내 모든 북마크 불러오기
    public List<BookmarkReadDto.Response> readAllMyBookmark() {
        Long accountId = accountContextService.getContextAccount().getAccountId();
        List<Bookmark> allMyBookmarks = bookmarkJpaRepository.findAllByAccount_AccountId(accountId);
        List<BookmarkReadDto.Response> allMyBookmarksReadDto
                = allMyBookmarks
                .stream()
                .map(bookmark -> BookmarkReadDto.Response.fromEntity(bookmark))
                .collect(Collectors.toList());

        return allMyBookmarksReadDto;
    }
}
