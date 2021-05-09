package com.se.pickple_api_server.v1.bookmark.application.error;

import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import lombok.Getter;

@Getter
public enum BookmarkErrorCode implements ErrorCode {

    NO_SUCH_BOOKMARK(400, "BM01", "존재하지 않는 태그"),
    DUPLICATED_BOOKMARK(401, "BM02", "이미 해당 게시물을 북마크");

    private int status;
    private final String code;
    private final String message;

    BookmarkErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

