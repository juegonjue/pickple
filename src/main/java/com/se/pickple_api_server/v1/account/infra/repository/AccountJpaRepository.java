package com.se.pickple_api_server.v1.account.infra.repository;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

    @Override
    Optional<Account> findById(Long id);
    Optional<Account> findByIdString(String idString);

}
