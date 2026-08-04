package com.example.FiltersIntroduction.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

//@Component
public class ResponseBodyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;

        ContentCachingResponseWrapper wrappedResponse=new ContentCachingResponseWrapper(httpServletResponse);

        filterChain.doFilter(servletRequest,servletResponse);

        byte[] originalBodyInBytes=wrappedResponse.getContentAsByteArray();

        String ogBody=new String(originalBodyInBytes);

        String modifiedBody=
                """
                {
                    "originalResponse" : %s,
                    "appName" : "sms"
                }
                """.formatted(ogBody);

        wrappedResponse.resetBuffer();

        wrappedResponse.getWriter().write(modifiedBody);
        wrappedResponse.copyBodyToResponse();

    }
}
