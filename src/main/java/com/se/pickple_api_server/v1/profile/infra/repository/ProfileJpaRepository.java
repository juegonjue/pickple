package com.se.pickple_api_server.v1.profile.infra.repository;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.profile.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByAccount(Account account);
}
