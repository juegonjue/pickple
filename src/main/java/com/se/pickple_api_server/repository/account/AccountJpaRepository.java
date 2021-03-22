package com.se.pickple_api_server.repository.account;

import com.se.pickple_api_server.domain.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
}