package com.se.pickple_api_server.v1.tag.application.service;


import com.se.pickple_api_server.v1.common.domain.error.ErrorCode;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
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

    public boolean delete(Long id){
        Tag tag = tagJpaRepository.findById(id).orElseThrow(()->new BusinessException(TagErrorCode.NO_SUCH_TAG));
        tagJpaRepository.delete(tag);
        return true;
    }

}
