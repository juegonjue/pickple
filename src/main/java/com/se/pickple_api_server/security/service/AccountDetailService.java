package com.se.pickple_api_server.security.service;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.domain.error.AccountErrorCode;
import com.se.pickple_api_server.v1.account.infra.repository.AccountJpaRepository;
import com.se.pickple_api_server.v1.common.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountDetailService implements UserDetailsService {

  private final AccountJpaRepository accountJpaRepository;

  @Value("${spring.security.anonymous.id}")
  private String ANONYMOUS_ID;

  @Value("${spring.security.anonymous.pw}")
  private String ANONYMOUS_PW;

  @Value("${spring.security.anonymous.auth}")
  private String ANONYMOUS_AUTH;

  @Override
  public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
    Account account = accountJpaRepository.findById(Long.parseLong(accountId))
        .orElseThrow(() -> new BusinessException(AccountErrorCode.NO_SUCH_ACCOUNT));
    List<GrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority(account.getType().toString()));
    return new User(account.getIdString(), account.getPassword(), grantedAuthorities);
  }

  public UserDetails loadDefaultGroupAuthorities() throws UsernameNotFoundException {
    List<GrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority(ANONYMOUS_AUTH));
    return new User(ANONYMOUS_ID, ANONYMOUS_PW, grantedAuthorities);
  }

  public boolean hasAuthority(String auth) {
    Set<String> authorities = AuthorityUtils
        .authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    return authorities.contains(auth);
  }

  public boolean isOwner(Account account) {
    if(SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getName() == null)
      throw new AccessDeniedException("비정상적인 접근");

    String id = SecurityContextHolder.getContext().getAuthentication().getName();

    if(id.equals(ANONYMOUS_ID))
      return false;
    if(!id.equals(account.getIdString()))
      return false;
    return true;
  }
}
