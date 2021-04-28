package com.se.pickple_api_server.v1.tag.infra.repository;

import com.se.pickple_api_server.v1.tag.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagJpaRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(String tagName);
    List<Tag> findByTagNameContaining(String keyword);

}

