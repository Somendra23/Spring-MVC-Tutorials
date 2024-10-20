package com.dailycodebuffer.transaction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dailycodebuffer.transaction.interceptor.EmployeeInterceptor;

@Configuration
public class Webconfig implements WebMvcConfigurer{

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new EmployeeInterceptor())
                .addPathPatterns("/employee/**"); // Specify the URL patterns to apply the interceptor
    }
}
