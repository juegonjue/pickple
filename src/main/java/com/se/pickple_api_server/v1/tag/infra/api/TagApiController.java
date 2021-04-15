package com.se.pickple_api_server.v1.tag.infra.api;


import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.tag.application.dto.TagCreateDto;
import com.se.pickple_api_server.v1.tag.application.service.TagCreateService;
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
@Api(tags = "태그 관리")
public class TagApiController {

    private final TagCreateService tagCreateService;

    @PostMapping(path = "/tag")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "태그 등록")
    public SuccessResponse<Long> create(@RequestBody @Validated TagCreateDto.Request request) {
        return new SuccessResponse(HttpStatus.CREATED.value(), "태그 등록에 성공했습니다", tagCreateService.create(request));
    }

}
