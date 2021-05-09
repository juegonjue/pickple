package com.se.pickple_api_server.v1.bookmark.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
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
public class BookmarkDeleteService {

    private final BookmarkJpaRepository bookmarkJpaRepository;
    private final AccountContextService accountContextService;

    @Transactional
    public boolean delete(Long bookmarkId) {
        Bookmark bookmark = bookmarkJpaRepository.findById(bookmarkId)
                .orElseThrow(()-> new BusinessException(BookmarkErrorCode.NO_SUCH_BOOKMARK));
        if (accountContextService.isOwner(bookmark.getAccount()))
            bookmarkJpaRepository.delete(bookmark);
        return true;
    }

}
