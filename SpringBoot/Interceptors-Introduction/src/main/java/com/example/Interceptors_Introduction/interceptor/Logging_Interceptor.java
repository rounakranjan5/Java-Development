package com.example.Interceptors_Introduction.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class Logging_Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Incoming Request ...");

        System.out.println("HTTP Method : "+request.getMethod());
        System.out.println("Request URI : "+request.getRequestURI());
        System.out.println("Request Parameters : "+request.getQueryString());
        System.out.println("Client IP : "+request.getRemoteAddr());
        System.out.println("Token Header : "+request.getHeader("token"));

        long startTime=System.currentTimeMillis();

        request.setAttribute("startTime",startTime);

        if(handler instanceof HandlerMethod handlerMethod){
            System.out.println("Controller : "+handlerMethod.getBeanType().getName());
            System.out.println("Controller Method : "+handlerMethod.getMethod().getName());
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handled Called...");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("After Completion Called...");
        System.out.println("Response Status : "+response.getStatus());
        System.out.println("API Response Time : "+( System.currentTimeMillis()- (Long) request.getAttribute("startTime")));

    }
}
