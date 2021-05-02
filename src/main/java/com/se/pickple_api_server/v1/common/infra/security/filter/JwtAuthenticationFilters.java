package com.se.pickple_api_server.v1.common.infra.security.filter;


import com.se.pickple_api_server.v1.common.infra.security.provider.JwtTokenResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilters extends GenericFilterBean {

  private final JwtTokenResolver jwtTokenResolver;

  // servletRequest통해 들어온다 ?
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String token = jwtTokenResolver.resolveToken((HttpServletRequest) request);
    Authentication auth;
      if (token != null && jwtTokenResolver.validateToken(token)) {
          auth = jwtTokenResolver.getAuthentication(token);
      } else {
//          auth = jwtTokenResolver.getDefaultAuthentication();
//         TODO 예외 처리
        auth = null;
//        return;
      }

    SecurityContextHolder.getContext().setAuthentication(auth);
    chain.doFilter(request, response);
  }
}
