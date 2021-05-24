package com.se.pickple_api_server.v1.tag.infra.api;


import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
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
import org.springframework.data.domain.Pageable;
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
    @ApiOperation(value = "UC-TG-01 태그 등록")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(path = "/tag")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated TagCreateDto.Request request) {
        System.out.println("UC-TG-01 요청");
        return new SuccessResponse(HttpStatus.CREATED.value(), "태그 등록에 성공했습니다", tagCreateService.create(request));
    }

    @ApiOperation(value = "UC-TG-02 [일반 사용자]태그 조회")
    @GetMapping(path="/tag")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<List<TagReadDto.Response>> readAll() {
        System.out.println("UC-TG-02 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "모든 태그 목록 조회에 성공하였습니다.", tagReadService.readAll());
    }

    @ApiOperation(value = "UC-TG-03 [관리자] 태그 검색")
    @GetMapping(path = "/tag/search")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readSearchTag(@Validated SearchDto.Request pageRequest) {
        System.out.println("UC-TG-03 요청");
        return new SuccessResponse(HttpStatus.OK.value(), "태그 조회 및 검색 성공", tagReadService.search(pageRequest));
    }

    // UC-TG-04 태그 삭제
    @ApiOperation(value = "UC-TG-04 태그 삭제")
    @DeleteMapping(path = "/tag/{tagId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse delete(@PathVariable(value = "tagId") Long id) {
        System.out.println("UC-TG-04 요청");
        tagDeleteService.delete(id);
        return new SuccessResponse(HttpStatus.OK.value(), "성공적으로 태그를 삭제하였습니다.");
    }

//    @ApiOperation(value = "UC-TG-03 [관리자]태그조회_페이징")
//    @GetMapping(path = "/tag")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @ResponseStatus(value = HttpStatus.OK)
//    public SuccessResponse<PageImpl> readAllPaging(@Validated PageRequest pageRequest) {
//        return new SuccessResponse(HttpStatus.OK.value(), "모든 태그 목록 조회(페이징)에 성공했습니다", tagReadService.readAllPaging(pageRequest.of()));
//    }

}
