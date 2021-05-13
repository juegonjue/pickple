package com.se.pickple_api_server.v1.bookmark.infra.api;

import com.se.pickple_api_server.v1.bookmark.application.dto.BookmarkCreateDto;
import com.se.pickple_api_server.v1.bookmark.application.dto.BookmarkReadDto;
import com.se.pickple_api_server.v1.bookmark.application.service.BookmarkCreateService;
import com.se.pickple_api_server.v1.bookmark.application.service.BookmarkDeleteService;
import com.se.pickple_api_server.v1.bookmark.application.service.BookmarkReadService;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "북마크 관리")
public class BookmarkApiController {

    private final BookmarkCreateService bookmarkCreateService;
    private final BookmarkReadService bookmarkReadService;
    private final BookmarkDeleteService bookmarkDeleteService;

    @ApiOperation(value = "북마크 등록")
    @PostMapping(path = "/bookmark")
    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBER')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated BookmarkCreateDto.Request request) {
        return new SuccessResponse(HttpStatus.CREATED.value(), "북마크 등록 성공", bookmarkCreateService.create(request));
    }


    @ApiOperation(value = "내 북마크 조회")
    @GetMapping(path = "/bookmark/my")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<BookmarkReadDto.MyResponse> readMyBookmark() {
        return new SuccessResponse(HttpStatus.OK.value(), "북마크 조회 성공", bookmarkReadService.readAllMyBookmark());
    }

    @ApiOperation(value = "북마크 해제")
    @DeleteMapping(path = "/bookmark/{bookmarkId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse deleteBookmark(@PathVariable(value = "bookmarkId") Long bookmarkId) {
        bookmarkDeleteService.delete(bookmarkId);
        return new SuccessResponse(HttpStatus.OK.value(), "북마크 해제 성공");
    }

    // 현재 모집글의 내 북마크 여부
    @ApiOperation(value = "현재 모집글에서의 내 북마크 여부")
    @GetMapping(path = "/bookmark/my/{boardId}")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<BookmarkReadDto.ExistResponse> readExist(@PathVariable(value = "boardId") Long boardId) {
        return new SuccessResponse(HttpStatus.OK.value(), "현재 모집글에서 내 북마크여부 조회 성공", bookmarkReadService.myBookmarkInRecboard(boardId));
    }
}
