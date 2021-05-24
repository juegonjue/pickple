package com.se.pickple_api_server.v1.tag.application.service;

import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.report.application.dto.ReportReadDto;
import com.se.pickple_api_server.v1.report.domain.entity.Report;
import com.se.pickple_api_server.v1.tag.application.dto.TagReadDto;
import com.se.pickple_api_server.v1.tag.application.error.TagErrorCode;
import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import com.se.pickple_api_server.v1.tag.infra.repository.TagJpaRepository;
import com.se.pickple_api_server.v1.tag.infra.repository.TagQueryRepository;
import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagReadService {

    private final TagJpaRepository tagJpaRepository;
    private final TagQueryRepository tagQueryRepository;

    // 사용자 모든 목록 조회
    public List<TagReadDto.Response> readAll() {
        List<Tag> allTags = tagJpaRepository.findAll();
        List<TagReadDto.Response> allTagsReadDto
                = allTags
                .stream()
                .map(tag -> TagReadDto.Response.fromEntity(tag))
                .collect(Collectors.toList());
        return allTagsReadDto;

    }

    // 모든 목록 조회, 페이징 처리까지
    public PageImpl readAllPaging(Pageable pageable) {
        Page<Tag> tags = tagJpaRepository.findAll(pageable);
        List<TagReadDto.Response> responseList = tags.stream()
                .map(tag -> TagReadDto.Response.fromEntity(tag))
                .collect(Collectors.toList());
        return new PageImpl(responseList, tags.getPageable(), tags.getTotalElements());
    }

    // 검색어 기반 조회
    public List<TagReadDto.Response> readMatchedKeyword(String keyword) {
        List<Tag> tags = tagJpaRepository.findAllByTagNameContainsIgnoreCase(keyword);
        return tags
                .stream()
                .map(tag -> TagReadDto.Response.fromEntity(tag))
                .collect(Collectors.toList());
    }

    // 태그 검색 페이징처리 (관리자)
    public PageImpl search(SearchDto.Request pageRequest) {
        Page<Tag> tagPage = tagQueryRepository.search(pageRequest);
        List<TagReadDto.Response> responseList = tagPage
                .get()
                .map(tag -> TagReadDto.Response.fromEntity(tag))
                .collect(Collectors.toList());
        return new PageImpl(responseList, tagPage.getPageable(), tagPage.getTotalElements());
    }

}
