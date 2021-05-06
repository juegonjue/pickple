package com.se.pickple_api_server.v1.profile.infra.api;


import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileCreateDto;
import com.se.pickple_api_server.v1.profile.application.service.ProfileCreateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "프로필 관리")
public class ProfileApiController {

    private final ProfileCreateService profileCreateService;

    @ApiOperation(value = "프로필 등록")
    @PostMapping(path = "/profile")
    //@PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated ProfileCreateDto.Request request) {
        return new SuccessResponse<>(HttpStatus.CREATED.value(), "프로필 등록 성공", profileCreateService.create(request));
    }


}
