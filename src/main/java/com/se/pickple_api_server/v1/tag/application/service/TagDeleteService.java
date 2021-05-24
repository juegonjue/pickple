package com.se.pickple_api_server.v1.tag.application.service;


import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import com.se.pickple_api_server.v1.profile.infra.repository.ProfileTagJpaRepository;
import com.se.pickple_api_server.v1.recruitment.infra.repository.RecruitmentBoardTagJpaRepository;
import com.se.pickple_api_server.v1.tag.application.error.TagErrorCode;
import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import com.se.pickple_api_server.v1.tag.infra.repository.TagJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagDeleteService {

    private final TagJpaRepository tagJpaRepository;
    private final RecruitmentBoardTagJpaRepository recruitmentBoardTagJpaRepository;
    private final ProfileTagJpaRepository profileTagJpaRepository;

    @Transactional
    public void delete(Long id){
        recruitmentBoardTagJpaRepository.deleteAllByTag_TagId(id);
        profileTagJpaRepository.deleteAllByTag_TagId(id);
        Tag tag = tagJpaRepository.findById(id).orElseThrow(()->new BusinessException(TagErrorCode.NO_SUCH_TAG));
        tagJpaRepository.delete(tag);
    }
}
