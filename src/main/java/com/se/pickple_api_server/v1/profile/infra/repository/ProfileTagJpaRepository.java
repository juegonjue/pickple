package com.se.pickple_api_server.v1.profile.infra.repository;

import com.se.pickple_api_server.v1.profile.domain.entity.ProfileTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileTagJpaRepository extends JpaRepository<ProfileTag, Long> {
    void deleteAllByTag_TagId(Long tagId);
}
