package com.se.pickple_api_server.v1.account.infra.repository;

import com.querydsl.jpa.JPQLQuery;
import com.se.pickple_api_server.v1.account.application.dto.AccountReadDto;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.domain.entity.QAccount;
import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountQueryRepositoryImpl extends QuerydslRepositorySupport implements AccountQueryRepository {
    public AccountQueryRepositoryImpl() { super(Account.class); }

    @Override
    public Page<Account> search(SearchDto.Account searchRequest) {

        QAccount account = QAccount.account;
        JPQLQuery query = from(account);

        if (searchRequest.getKeyword() != null) {
            query.where(
                    account.name.containsIgnoreCase(searchRequest.getKeyword())
                    .or(account.idString.containsIgnoreCase(searchRequest.getKeyword()))
            );
        }
        if (searchRequest.getAccountType() != null) {
            query.where(account.accountType.eq(searchRequest.getAccountType()));
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<Account> accountList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();

        return new PageImpl(accountList, pageable, totalElement);
    }
}
