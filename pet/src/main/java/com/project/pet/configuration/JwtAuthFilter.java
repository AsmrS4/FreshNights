package com.project.pet.configuration;

import com.project.pet.services.token.AccessTokenService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final AccessTokenService accessTokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String AUTHORIZATION_HEADER = "Authorization";
        String BEARER_PREFIX = "Bearer ";
        int START_TOKEN_INDEX = BEARER_PREFIX.length();

        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if(authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            String accessToken = authHeader.substring(START_TOKEN_INDEX);
            UUID userId = accessTokenService.getUserId(accessToken);
            if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userId, accessToken, AuthorityUtils.createAuthorityList(accessTokenService.getUserRole(accessToken))
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
