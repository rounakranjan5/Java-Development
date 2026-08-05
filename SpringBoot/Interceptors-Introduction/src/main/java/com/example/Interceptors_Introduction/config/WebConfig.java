package com.example.Interceptors_Introduction.config;

import com.example.Interceptors_Introduction.interceptor.AuthenticationInterceptor;
import com.example.Interceptors_Introduction.interceptor.AuthorizationInterceptor;
import com.example.Interceptors_Introduction.interceptor.Logging_Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public Logging_Interceptor loggingInterceptor;
    public AuthenticationInterceptor authenticationInterceptor;
    public AuthorizationInterceptor authorizationInterceptor;

    public WebConfig(Logging_Interceptor loggingInterceptor, AuthenticationInterceptor authenticationInterceptor ,AuthorizationInterceptor authorizationInterceptor) {
        this.authenticationInterceptor=authenticationInterceptor;
        this.loggingInterceptor = loggingInterceptor;
        this.authorizationInterceptor=authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor).order(3);

        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/login","api/public/**").order(1);

        registry.addInterceptor(authorizationInterceptor).order(2);
    }
}
