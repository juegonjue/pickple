package com.se.pickple_api_server.v1.account.infra.repository;

import com.se.pickple_api_server.v1.account.application.dto.AccountReadDto;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import org.springframework.data.domain.Page;

public interface AccountQueryRepository {
    Page<Account> search(SearchDto.Account request);
}
