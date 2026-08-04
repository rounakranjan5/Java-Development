package com.example.FiltersIntroduction.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class ResponseHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;

        String reqId= UUID.randomUUID().toString();

        httpServletResponse.setHeader("req-id",reqId);

        filterChain.doFilter(servletRequest,servletResponse);

    }
}
