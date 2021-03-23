package com.se.pickple_api_server.v1.account.infra.repository;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<Account, Long> {
}