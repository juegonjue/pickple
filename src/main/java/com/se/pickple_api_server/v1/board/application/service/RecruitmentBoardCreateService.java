package com.se.pickple_api_server.v1.board.application.service;

import com.se.pickple_api_server.v1.account.application.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.board.application.dto.RecruitmentBoardCreateDto;
import com.se.pickple_api_server.v1.board.domain.entity.RecruitmentBoard;
import com.se.pickple_api_server.v1.board.infra.repository.RecruitmentBoardJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.recboard_tag.domain.entity.RecruitmentBoardTag;
import com.se.pickple_api_server.v1.tag.application.error.TagErrorCode;
import com.se.pickple_api_server.v1.tag.infra.repository.TagJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitmentBoardCreateService {

    private final RecruitmentBoardJpaRepository recruitmentBoardJpaRepository;
    private final TagJpaRepository tagJpaRepository;
    private final AccountJpaRepository accountJpaRepository;

    @Transactional
    public Long create(RecruitmentBoardCreateDto.Request request) {
        LocalDateTime now = LocalDateTime.now();
        Account account = getWriter(request.getWriterId());
        List<RecruitmentBoardTag> tags = getTags(request.getTagList());
        RecruitmentBoard recruitmentBoard = new RecruitmentBoard(
                account,
                request.getTitle(),
                request.getText(),
                request.getBoardType(),
                0,
                0,
                request.getRecNumber(),
                0,
                request.getPaymentMax(),
                request.getWorkStartDate(),
                request.getWorkEndDate(),
                now,
                request.getRecEndDate(),
                tags
        );

        recruitmentBoardJpaRepository.save(recruitmentBoard);
        return recruitmentBoard.getBoardId();
    }


    private Account getWriter(RecruitmentBoardCreateDto.Account account) {
        return accountJpaRepository.findById(account.getWriterId()).orElseThrow(()->new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
    }

    private List<RecruitmentBoardTag> getTags(List<RecruitmentBoardCreateDto.TagDto> tagDtoList) {
        return tagDtoList.stream()
                .map(tagDto ->
                        RecruitmentBoardTag.builder()
                        .tag(tagJpaRepository.findById(tagDto.getTagId())
                                .orElseThrow(() -> new BusinessException(TagErrorCode.NO_SUCH_TAG)))
                        .build())
                .collect(Collectors.toList());
    }


}
