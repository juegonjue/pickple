package com.se.pickple_api_server.v1.recruitment.application.service;

import com.se.pickple_api_server.v1.account.application.service.AccountContextService;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.domain.error.GlobalErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoardTag;
import com.se.pickple_api_server.v1.recruitment.application.dto.RecruitmentBoardUpdateDto;
import com.se.pickple_api_server.v1.recruitment.application.error.BoardErrorCode;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.tag.application.dto.TagCreateDto;
import com.se.pickple_api_server.v1.tag.application.error.TagErrorCode;
import com.se.pickple_api_server.v1.tag.infra.repository.TagJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitmentBoardUpdateService {

    private final AccountContextService accountContextService;
    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;
    private final TagJpaRepository tagJpaRepository;

    @Transactional
    public Long update(RecruitmentBoardUpdateDto.Request request) {
        RecruitmentBoard recruitmentBoard = recruitmentBoardJpaRepository.findById(request.getBoardId())
                .orElseThrow(() -> new BusinessException(BoardErrorCode.NO_SUCH_BOARD));
        Account account = recruitmentBoard.getAccount();

        if (!(accountContextService.isOwner(account)))
            throw new BusinessException(GlobalErrorCode.HANDLE_ACCESS_DENIED);

        List<RecruitmentBoardTag> tags = getTags(request.getTagList());

        recruitmentBoard.update(
                request.getNewTitle(),
                request.getNewText(),
                request.getNewRecNumber(),
                request.getNewPaymentMax(),
                request.getNewWorkStartDate(),
                request.getNewWorkEndDate(),
                request.getNewRecStartDate(),
                request.getNewRecEndDate(),
                tags
        );

        recruitmentBoardJpaRepository.save(recruitmentBoard);
        return recruitmentBoard.getBoardId();
    }

    private List<RecruitmentBoardTag> getTags(List<TagCreateDto.TagDto> tagDtoList) {
        return tagDtoList.stream()
                .map(tag -> RecruitmentBoardTag.builder()
                    .tag(tagJpaRepository.findById(tag.getTagId())
                        .orElseThrow(() -> new BusinessException(TagErrorCode.NO_SUCH_TAG)))
                    .build()
                )
                .collect(Collectors.toList());
    }

}
