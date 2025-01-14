package org.signaling.signaling_server.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.signaling.signaling_server.common.exception.NotFoundException;
import org.signaling.signaling_server.common.exception.UnauthorizedException;
import org.signaling.signaling_server.common.type.error.AuthErrorType;
import org.signaling.signaling_server.common.utils.JwtUtils;
import org.signaling.signaling_server.domain.member.dto.CustomUserDetail;
import org.signaling.signaling_server.domain.member.repository.MemberRepository;
import org.signaling.signaling_server.entity.member.MemberEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        if (uri.contains("/open-api")
                || uri.contains("/swagger-ui")
                || uri.contains("/v3/api-docs")
                || uri.contains("/static")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = null;
        Long jwtId = null;
        String jwtSub = null;

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.error("Authorization 헤더 누락 또는 토큰 형식 오류");
            throw new UnauthorizedException(AuthErrorType.HEADER_INVALID);
        }

        jwtToken = authorizationHeader.substring(7);

        jwtSub = jwtUtils.getSubject(jwtToken);
        if(uri.contains("/api/auth/refresh-token") && !jwtSub.equals("refreshToken")){
            throw new UnauthorizedException(AuthErrorType.HEADER_INVALID);
        }

        // access token 블랙리스트 확인
        if (jwtUtils.blackListAccessToken(jwtToken)) {
            throw new UnauthorizedException(AuthErrorType.HEADER_INVALID);
        }

        jwtId = jwtUtils.getIdFromToken(jwtToken);
        log.debug("jwt : ", jwtToken);

        if (jwtId == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        MemberEntity entity =
                memberRepository
                        .findById(jwtId)
                        .orElseThrow(() -> new NotFoundException(AuthErrorType.NOT_FOUND));

        log.debug(entity.getEmail());
        if (!jwtUtils.validateToken(jwtToken, entity)) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return;
        }

        CustomUserDetail customUserDetail = new CustomUserDetail(entity);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        customUserDetail, null, customUserDetail.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext()
                .setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);
    }
}