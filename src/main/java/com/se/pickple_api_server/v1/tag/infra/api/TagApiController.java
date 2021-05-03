package com.se.pickple_api_server.v1.tag.infra.api;


import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.tag.application.dto.TagCreateDto;
import com.se.pickple_api_server.v1.tag.application.dto.TagReadDto;
import com.se.pickple_api_server.v1.tag.application.service.TagCreateService;
import com.se.pickple_api_server.v1.tag.application.service.TagDeleteService;
import com.se.pickple_api_server.v1.tag.application.service.TagReadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "태그 관리")
public class TagApiController {

    private final TagCreateService tagCreateService;
    private final TagReadService tagReadService;
    private final TagDeleteService tagDeleteService;


    // UC-TG-01 태그 등록
    @ApiOperation(value = "태그 등록")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(path = "/tag")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated TagCreateDto.Request request) {
        return new SuccessResponse(HttpStatus.CREATED.value(), "태그 등록에 성공했습니다", tagCreateService.create(request));
    }

    // UC-TG-02 태그(전체) 조회_페이징
    @ApiOperation(value = "태그 조회 _ 페이징")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(path = "/tag")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<PageImpl> readAllPaging(@Validated PageRequest pageRequest) {
        return new SuccessResponse(HttpStatus.OK.value(), "모든 태그 목록 조회(페이징)에 성공했습니다", tagReadService.readAllPaging(pageRequest.of()));
    }

    // UC-TG-03 태그 검색(어 기반 조회)
    @ApiOperation(value = "태그 조회 (검색어기반)")
    @PostMapping(path = "/tag/search/{keyword}")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<List<TagReadDto.Response>> readKeyword(@PathVariable(name = "keyword") String keyword ) {
        return new SuccessResponse(HttpStatus.OK.value(), "검색어 기반 태그 목록 조회 성공", tagReadService.readMatchedKeyword(keyword));
    }



    // UC-TG-04 태그 삭제F
    @ApiOperation(value = "태그 삭제")
    @DeleteMapping(path = "/tag/{tagId}")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse delete(@PathVariable(value = "tagId") Long id) {
        tagDeleteService.delete(id);
        return new SuccessResponse(HttpStatus.OK.value(), "성공적으로 태그를 삭제하였습니다.");
    }

    //+) UC-TG-05 태그(전체) 조회
    @ApiOperation(value = "태그 조회")
    @GetMapping(path="/tag/all")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<List<TagReadDto.Response>> readAll() {
        return new SuccessResponse(HttpStatus.OK.value(), "모든 태그 목록 조회에 성공하였습니다.", tagReadService.readAll());
    }

}
