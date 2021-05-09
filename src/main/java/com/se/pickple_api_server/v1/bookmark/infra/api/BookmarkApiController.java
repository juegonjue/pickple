package com.se.pickple_api_server.v1.bookmark.infra.api;

import com.se.pickple_api_server.v1.bookmark.application.dto.BookmarkCreateDto;
import com.se.pickple_api_server.v1.bookmark.application.dto.BookmarkReadDto;
import com.se.pickple_api_server.v1.bookmark.application.service.BookmarkCreateService;
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

    @ApiOperation(value = "북마크 등록")
    @PostMapping(path = "/bookmark")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated BookmarkCreateDto.Request request) {
        return new SuccessResponse(HttpStatus.CREATED.value(), "북마크 등록 성공", bookmarkCreateService.create(request));
    }


    @ApiOperation(value = "내 모든 북마크 불러오기")
    @GetMapping(path = "/bookmark")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<BookmarkReadDto.Response> readMyBookmark() {
        return new SuccessResponse(HttpStatus.OK.value(), "북마크 조회 성공", bookmarkReadService.readAllMyBookmark());
    }

}
