package com.example.block.security.filter;

import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.global.apiPayload.exception.GeneralException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        try {
            filterChain.doFilter(request, response);
        } catch (SecurityException e) {
            log.error("FilterException throw SecurityException Exception : {}", e.getMessage());
            request.setAttribute("exception", ErrorStatus.ACCESS_DENIED.getReasonHttpStatus());
            filterChain.doFilter(request, response);
        } catch (MalformedJwtException e) {
            log.error("FilterException throw MalformedJwtException Exception : {}", e.getMessage());
            request.setAttribute("exception", ErrorStatus.TOKEN_MALFORMED_ERROR.getReasonHttpStatus());
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            log.error("FilterException throw IllegalArgumentException Exception : {}", e.getMessage());
            request.setAttribute("exception", ErrorStatus.TOKEN_TYPE_ERROR.getReasonHttpStatus());
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.error("FilterException throw ExpiredJwtException Exception : {}", e.getMessage());
            request.setAttribute("exception", ErrorStatus.EXPIRED_TOKEN_ERROR.getReasonHttpStatus());
            filterChain.doFilter(request, response);
        } catch (UnsupportedJwtException e) {
            log.error("FilterException throw UnsupportedJwtException Exception : {}", e.getMessage());
            request.setAttribute("exception", ErrorStatus.TOKEN_UNSUPPORTED_ERROR.getReasonHttpStatus());
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            log.error("FilterException throw JwtException Exception : {}", e.getMessage());
            request.setAttribute("exception", ErrorStatus.TOKEN_UNKNOWN_ERROR.getReasonHttpStatus());
            filterChain.doFilter(request, response);
        } catch (GeneralException e) {
            log.error("FilterException throw Exception Exception : {}", e.getMessage());
            request.setAttribute("exception", e.getErrorReasonHttpStatus() != null ? e.getErrorReason() : ErrorStatus._INTERNAL_SERVER_ERROR.getReasonHttpStatus());
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("FilterException throw Exception Exception : {}", e.getMessage());
            request.setAttribute("exception", ErrorStatus._INTERNAL_SERVER_ERROR.getReasonHttpStatus());
            filterChain.doFilter(request, response);
        }
    }
}
